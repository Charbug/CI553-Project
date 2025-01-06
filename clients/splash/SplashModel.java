package clients.splash;

import catalogue.BetterBasket;
import catalogue.Product;
import debug.DEBUG;
import middle.*;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Implements the Model of the cashier client
 */
public class SplashModel extends Observable {
    public void askForUpdate() {
    }

    private enum State {process, checked}

    private State theState = State.process;   // Current state
    private Product theProduct = null;            // Current product
    private BetterBasket theBasket = null;            // Bought items

    private String pn = "";                      // Product being processed

    private StockReadWriter theStock = null;
    private OrderProcessing theOrder = null;

    /**
     * Construct the model of the Cashier
     *
     * @param mf The factory to create the connection objects
     */

    public SplashModel(MiddleFactory mf) {
        try                                           //
        {
            theStock = mf.makeStockReadWriter();        // Database access
            theOrder = mf.makeOrderProcessing();        // Process order
        } catch (Exception e) {
            DEBUG.error("CashierModel.constructor\n%s", e.getMessage());
        }
        theState = State.process;                  // Current state
    }


    public void loadCashier() {
/*
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
                    theProduct.setQuantity(amount);     //    & quantity
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
 */
        System.out.println("loadCashier");
    }

    public void loadCustomer()  {
/*
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
                    theProduct.setQuantity(amount);     //    & quantity
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
 */
        System.out.println("loadCustomer");

    }

    public void loadPacking()  {
/*
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
                    theProduct.setQuantity(amount);     //    & quantity
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
 */
        System.out.println("loadPacking");
    }

    public void loadBackdoor()  {
/*
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
                    theProduct.setQuantity(amount);     //    & quantity
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
 */
        System.out.println("loadBackdoor");
    }



}
  
