package clients.cashier;

import catalogue.BetterBasket;
import catalogue.Product;
import debug.DEBUG;
import middle.*;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Implements the Model of the cashier client
 */
public class CashierModel extends Observable {
    private enum State {process, checked}

    private State theState = State.process;   // Current state
    private Product theProduct = null;            // Current product
    private BetterBasket theBasket = null;            // Bought items

    private String pn = "";                      // Product being processed

    private StockReadWriter theStock = null;
    private OrderProcessing theOrder = null;
    private MiddleFactory midFact = null;

    /**
     * Construct the model of the Cashier
     *
     * @param mf The factory to create the connection objects
     */

    public CashierModel(MiddleFactory mf) {
        try                                           //
        {
            theStock = mf.makeStockReadWriter();        // Database access
            theOrder = mf.makeOrderProcessing();        // Process order
            midFact = mf;
        } catch (Exception e) {
            DEBUG.error("CashierModel.constructor\n%s", e.getMessage());
        }
        theState = State.process;                  // Current state
    }

    /**
     * Get the Basket of products
     *
     * @return basket
     */
    public BetterBasket getBasket() {
        return theBasket;
    }

    /**
     * Check if the product is in Stock
     *
     * @param productNum The product number
     */
    public void doCheck(String productNum) {
        String theAction = "";
        theState = State.process;                  // State process
        pn = productNum.trim();                    // Product no.
        int amount = 1;                         //  & quantity
        try {
            if (theStock.exists(pn))              // Stock Exists?
            {                                         // T
                Product pr = theStock.getDetails(pn);   //  Get details
                if (pr.getQuantity() >= amount)       //  In stock?
                {                                       //  T
                    theAction =                           //   Display
                            String.format("%s : %7.2f (%2d) ", //
                                    pr.getDescription(),              //    description
                                    pr.getPrice(),                    //    price
                                    pr.getQuantity());               //    quantity
                    theProduct = pr;                      //   Remember prod.
                    theState = State.checked;             //   OK await BUY
                } else {                                //  F
                    theAction =                           //   Not in Stock
                            pr.getDescription() + " not in stock";
                }
            } else {                                  // F Stock exists
                theAction =                             //  Unknown
                        "Unknown product number " + pn;       //  product no.
            }
        } catch (StockException e) {
            DEBUG.error("%s\n%s",
                    "CashierModel.doCheck", e.getMessage());
            theAction = e.getMessage();
        }
        setChanged();
        notifyObservers(theAction);
    }

    /**
     * Buy the product
     */
    public void doBuy(Object quantity) {
        int amountPurchased = (int) quantity;
        String theAction = "";

        try {
            if (theState != State.checked)          // Not checked
            {                                         //  with customer
                theAction = "please check its availability";
            } else {
                if (amountPurchased > theProduct.getQuantity()) {
                    theAction = "Not enough stock!";
                } else {
                    theProduct.setQuantity(amountPurchased);
                    boolean stockBought =                   // Buy
                            theStock.buyStock(                    //  however
                                    theProduct.getProductNum(),         //  may fail
                                    amountPurchased);         //
                    if (stockBought)                      // Stock bought
                    {                                       // T
                        makeBasketIfReq();                    //  new Basket ?
                        theBasket.add(theProduct);          //  Add to bought
                        theAction = "Purchased " +            //    details
                                theProduct.getDescription();  //
                    } else {                                // F
                        theAction = "!!! Not in stock";       //  Now no stock
                    }
                }
            }
        } catch (StockException e) {
            DEBUG.error("%s\n%s",
                    "CashierModel.doBuy", e.getMessage());
            theAction = e.getMessage();
        }
        theState = State.process;                   // All Done
        setChanged();
        notifyObservers(theAction);
    }

    /**
     * Customer pays for the contents of the basket
     */
    public void doBought() {
        String theAction = "";
        int amount = 1;                       //  & quantity
        try {
            if (theBasket != null &&
                    theBasket.size() >= 1)            // items > 1
            {                                       // T
                theOrder.newOrder(theBasket);       //  Process order
                theBasket = null;                     //  reset
            }                                       //
            theAction = "Start New Order";            // New order
            theState = State.process;               // All Done
            theBasket = null;
        } catch (OrderException e) {
            DEBUG.error("%s\n%s",
                    "CashierModel.doCancel", e.getMessage());
            theAction = e.getMessage();
        }
        theBasket = null;
        setChanged();
        notifyObservers(theAction); // Notify
    }

    /**
     * ask for update of view callled at start of day
     * or after system reset
     */
    public void askForUpdate() {
        setChanged();
        notifyObservers("Welcome");
    }

    /**
     * make a Basket when required
     */
    private void makeBasketIfReq() {
        if (theBasket == null) {
            try {
                int uon = theOrder.uniqueNumber();     // Unique order num.
                theBasket = makeBasket();                //  basket list
                theBasket.setOrderNum(uon);            // Add an order number
            } catch (OrderException e) {
                DEBUG.error("Comms failure\n" +
                        "CashierModel.makeBasket()\n%s", e.getMessage());
            }
        }
    }

    /**
     * return an instance of a new Basket
     *
     * @return an instance of a new Basket
     */
    protected BetterBasket makeBasket() {
        return new BetterBasket();
    }

    /* Creating Array of Product ID's to populate Combo Box in view */
    public String[] generateComboItems() throws StockException {
        ArrayList<String> output = new ArrayList<String>();
        boolean comboFull = false; /* Flag for while loop, set true when ID not found in database */
        int index = 1; /* Index starts at 1 as first product ID is '0001' */

        while (!comboFull) {
            String productID = "000" + String.valueOf(index); /* Appending leading 0s for ID */
            productID = productID.substring(productID.length() - 4);  /* sets ID to 4 digits, trimming excess 0s */

            /* Checks ID is in database, adds to combo if it is, or ends while loop if it's not */
            if (theStock.exists(productID)) {
                output.add(productID);
            } else {
                comboFull = true;
            }
            index++;

        }
        return output.toArray(new String[0]);
    }

    /**
     * Navigates to Splash Screen
     */
    public void returnButton() {
        midFact.getFrame("cashierFrame").setVisible(false);
        midFact.getFrame("splashFrame").setVisible(true);

    }
}
  
