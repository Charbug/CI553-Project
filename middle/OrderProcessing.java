package middle;

import catalogue.BetterBasket;

import java.util.List;
import java.util.Map;

/**
  * Defines the interface for accessing the order processing system.
  * @author  Mike Smith University of Brighton
  * @version 2.0
  */

public interface OrderProcessing
{
  /**
   * Create new order
   * @param bought BetterBasket Object, supplies items to buy
   * @throws OrderException OrderException
   */
                                                   // Used by
  public void newOrder(BetterBasket bought)              // Cashier
         throws OrderException;

  /**
   * Gets unique order number
   * @return Unique Number
   * @throws OrderException OrderException
   */

  public int  uniqueNumber()                       // Cashier
         throws OrderException;

  /**
   * Returns an order to pick from the warehouse
   * if no order then returns null.
   * @throws OrderException OrderException
   */

  public BetterBasket getOrderToPack()                   // Packer
         throws OrderException;

  /**
   * Informs the order processing system that the order has been
   * picked and the products are now on the conveyor belt to
   * the shop floor.
   * @throws OrderException OrderException
   */

  public boolean informOrderPacked(int orderNum)   // Packer 
         throws OrderException;

  /**
   * not being used in this version
   */

  public boolean informOrderCollected(int orderNum) // Collection
         throws OrderException;

  /**
   * not being used in this version
   */

  public Map<String,List<Integer>> getOrderState() // Display
         throws OrderException;
}
