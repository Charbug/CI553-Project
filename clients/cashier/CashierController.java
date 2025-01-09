package clients.cashier;


import middle.StockException;

/**
 * The Cashier Controller
 */

public class CashierController
{
  private CashierModel model = null;
  private CashierView  view  = null;

  /**
   * Constructor
   * @param model The model 
   * @param view  The view from which the interaction came
   */
  public CashierController( CashierModel model, CashierView view )
  {
    this.view  = view;
    this.model = model;
  }

  /**
   * Check interaction from view
   * @param pn The product number to be checked
   */
  public void doCheck( String pn )
  {
    model.doCheck(pn);
  }

   /**
   * Buy interaction from view
   */
  public void doBuy(Object quantity)
  {
    model.doBuy(quantity);
  }
  
   /**
   * Bought interaction from view
   */
  public void doBought()
  {
    model.doBought();
  }

  /**
   * Populate Combobox with IDs from the database
   */

  public void populateComboBox() throws StockException {
    view.populateComboBox(model.generateComboItems());
  }

  /**
   * Navigates to Splash Screen
   */

  public void returnButton() { model.returnButton(); }
}
