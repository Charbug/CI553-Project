package clients.customer;

import middle.StockException;

/**
 * The Customer Controller
 */

public class CustomerController
{
  private CustomerModel model = null;
  private CustomerView  view  = null;

  /**
   * Constructor
   * @param model The model 
   * @param view  The view from which the interaction came
   */
  public CustomerController( CustomerModel model, CustomerView view )
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
   * Clear interaction from view
   */
  public void doClear()
  {
    view.doClear();
    model.doClear();
  }

  /**
   * Populate Combobox with IDs from the database
   * @throws StockException Stock Exception if stock cannot be accessed
   */

  public void populateComboBox() throws StockException {
    view.populateComboBox(model.generateComboItems());
  }

  /**
   * Navigates to Splash Screen
   */

  public void returnButton() {
    doClear();
    model.returnButton(); }
}

