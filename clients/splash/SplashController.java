package clients.splash;


/**
 * The Cashier Controller
 */

public class SplashController
{
  private SplashModel model = null;
  private SplashView view  = null;

  /**
   * Constructor
   * @param model The model 
   * @param view  The view from which the interaction came
   */
  public SplashController(SplashModel model, SplashView view )
  {
    this.view  = view;
    this.model = model;
  }

  /**
   *
   * Navigates to Cashier Screen
   */
  public void loadCashier(  )
  {
    model.loadCashier();
  }

  /**
   *
   * Navigates to Cashier Screen
   */
  public void loadCustomer(  )
  {
    model.loadCustomer();
  }

  /**
   *
   * Navigates to Cashier Screen
   */
  public void loadPacking(  )
  {
    model.loadPacking();
  }

  /**
   *
   * Navigates to Cashier Screen
   */
  public void loadBackdoor(  )
  {
    model.loadBackdoor();
  }}
