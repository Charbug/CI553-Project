/**
 * @author  Mike Smith University of Brighton
 * Interface Middle factory
 * @version 2.0
 */

package middle;

import javax.swing.*;
import java.awt.*;

/**
  * Provide access to middle tier components.
  */

// Pattern: Abstract Factory

public interface MiddleFactory
{
 
  /**
   * Return an object to access the database for read only access
   * @return instance of StockReader
   * @throws StockException if issue
   */
  public StockReader makeStockReader() throws StockException;

  /**
   * Return an object to access the database for read/write access
   * @return instance of StockReadWriter
   * @throws StockException if issue
   */
  public StockReadWriter makeStockReadWriter() throws StockException;


  /**
   * Adds built frame to Hash Map for navigation purposes
   * All users share this same object.
   * @param name Frame Identifier - used as Hashmap key
   * @param frame Built frame object - used as Hashmap Value
   */

  public void addFrame(String name, JFrame frame);


  /**
   * Gets frame from Hash Map for navigation purposes
   * All users share this same object.
   * @param name Frame Identifier - used as Hashmap key
   */

  public JFrame getFrame(String name);

  /**
   * Return an object to access the order processing system
   * @return instance of OrderProcessing
   * @throws OrderException if issue
   */
  public OrderProcessing makeOrderProcessing() throws OrderException;

}

