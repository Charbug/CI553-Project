/**
 * @author  Mike Smith University of Brighton
 * @version 2.1
 */

package middle;

import dbAccess.StockR;
import dbAccess.StockRW;
import orders.Order;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;


/**
  * Provide access to middle tier components.
  * Now only one instance of each middle tier object is created
  */

// Pattern: Abstract Factory

public class LocalMiddleFactory implements MiddleFactory
{
  private static StockR  aStockR  = null;
  private static StockRW aStockRW = null;
  private static Order   aOrder   = null;

  /* Hash Map of frames, allows for screen navigation */
  public Map<String, JFrame> frameMap = new HashMap<String, JFrame>();
  
  /**
   * Return an object to access the database for read only access.
   * All users share this same object.
   */
  
  public StockReader makeStockReader() throws StockException
  {
    if ( aStockR == null )
      aStockR = new StockR();
    return aStockR;
  }

  /**
   * Return an object to access the database for read/write access.
   * All users share this same object.
   */
  
  public StockReadWriter makeStockReadWriter() throws StockException
  {
    if ( aStockRW == null )
      aStockRW = new StockRW();
    return aStockRW;
  }
  
  /**
   * Return an object to access the order processing system.
   * All users share this same object.
   */
   
  public OrderProcessing makeOrderProcessing() throws OrderException
  {
    if ( aOrder == null )
      aOrder = new Order();
    return aOrder;
  }

  /**
   * Adds built frame to Hash Map for navigation purposes
   * All users share this same object.
   * @param name Frame Identifier - used as Hashmap key
   * @param frame Built frame object - used as Hashmap Value
   */

  public void addFrame(String name, JFrame frame){
    frameMap.put(name, frame);
  }

  /**
   * Gets frame from Hash Map for navigation purposes
   * All users share this same object.
   * @param name Frame Identifier - used as Hashmap key
   */

  public JFrame getFrame(String name){
    return frameMap.get(name);
  }
}







