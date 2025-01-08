package clients.backDoor;

import catalogue.Basket;
import catalogue.BetterBasket;
import catalogue.Product;
import debug.DEBUG;
import middle.MiddleFactory;
import middle.StockException;
import middle.StockReadWriter;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Implements the Model of the back door client
 */
public class BackDoorModel extends Observable
{
  private Basket      theBasket  = null;            // Bought items
  private String      pn = "";                      // Product being processed

  private StockReadWriter theStock     = null;
  private MiddleFactory  midFact   = null;

  /*
   * Construct the model of the back door client
   * @param mf The factory to create the connection objects
   */

  public BackDoorModel(MiddleFactory mf)
  {
    try                                           // 
    {      
      theStock = mf.makeStockReadWriter();        // Database access
      midFact = mf;
    } catch ( Exception e )
    {
      DEBUG.error("CustomerModel.constructor\n%s", e.getMessage() );
    }

    theBasket = makeBasket();                     // Initial Basket
  }
  
  /**
   * Get the Basket of products
   * @return basket
   */
  public Basket getBasket()
  {
    return theBasket;
  }

  /**
   * Check The current stock level
   * @param productNum The product number
   */
  public void doCheck(String productNum )
  {
    pn  = productNum.trim();                    // Product no.
  }

  /**
   * Query 
   * @param productNum The product number of the item
   */
  public void doQuery(String productNum )
  {
    String theAction = "";
    pn  = productNum.trim();                    // Product no.
    try
    {                 //  & quantity
      if ( theStock.exists( pn ) )              // Stock Exists?
      {                                         // T
        Product pr = theStock.getDetails( pn ); //  Product
        theAction =                             //   Display 
          String.format( "%s : %7.2f (%2d) ",   //
          pr.getDescription(),                  //    description
          pr.getPrice(),                        //    price
          pr.getQuantity() );                   //    quantity
      } else {                                  //  F
        theAction =                             //   Inform
          "Unknown product number " + pn;       //  product number
      } 
    } catch( StockException e )
    {
      theAction = e.getMessage();
    }
    setChanged(); notifyObservers(theAction);
  }

  /**
   * Re stock 
   * @param productNum The product number of the item
   * @param quantity How many to be added
   */
  public void doRStock(String productNum, Object quantity )
  {
    int intQuantity = (int)quantity;
    String strQuantity = String.valueOf(intQuantity);
    String theAction = "";
    theBasket = makeBasket();
    pn  = productNum.trim();                    // Product no.
    String pn  = productNum.trim();             // Product no.
    int amount = 0;
    try
    {
      String aQuantity = strQuantity.trim();
      try
      {
        amount = Integer.parseInt(aQuantity);   // Convert
        if ( amount < 0 )
          throw new NumberFormatException("-ve");
      }
      catch ( Exception err)
      {
        theAction = "Invalid quantity";
        setChanged(); notifyObservers(theAction);
        return;
      }
  
      if ( theStock.exists( pn ) )              // Stock Exists?
      {                                         // T
        theStock.addStock(pn, amount);          //  Re stock
        Product pr = theStock.getDetails(pn);   //  Get details
        theBasket.add(pr);                      //
        theAction = "";                         // Display 
      } else {                                  // F
        theAction =                             //  Inform Unknown
          "Unknown product number " + pn;       //  product number
      } 
    } catch( StockException e )
    {
      theAction = e.getMessage();
    }
    setChanged(); notifyObservers(theAction);
  }

  /**
   * Clear the product()
   */
  public void doClear()
  {
    String theAction = "";
    theBasket.clear();                        // Clear s. list
    theAction = "Enter Product Number";       // Set display
    setChanged();
    notifyObservers(theAction);  // inform the observer view that model changed
  }
  
  /**
   * return an instance of a Basket
   * @return a new instance of a Basket
   */
  protected Basket makeBasket()
  {
    return new Basket();
  }

  public String[] generateComboItems() throws StockException {
    ArrayList<String> output = new ArrayList<>();
    boolean comboFull = false; /* Flag for while loop, set true when ID not found in database */
    int index = 1; /* Index starts at 1 as first product ID is '0001' */

    while (!comboFull) {
      String productID = "000" + String.valueOf(index); /* Appending leading 0s for ID */
      productID = productID.substring(productID.length()-4);  /* sets ID to 4 digits, trimming excess 0s */

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

  public void returnButton() {
    midFact.getFrame("backdoorFrame").setVisible(false);
    midFact.getFrame("splashFrame").setVisible(true);

  }
}

