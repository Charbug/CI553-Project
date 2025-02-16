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
                                                   // Used by
  public void newOrder(BetterBasket bought)              // Cashier
         throws OrderException;

  public int  uniqueNumber()                       // Cashier
         throws OrderException;
   
  public BetterBasket getOrderToPack()                   // Packer
         throws OrderException;
 
  public boolean informOrderPacked(int orderNum)   // Packer 
         throws OrderException;
         
  // not being used in this version
  public boolean informOrderCollected(int orderNum) // Collection
         throws OrderException;
   
  // not being used in this version
  public Map<String,List<Integer>> getOrderState() // Display
         throws OrderException;
}
