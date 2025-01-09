package clients.splash;


/**
 * The Cashier Controller
 */

public class SplashController
{
  private final SplashModel model;

    /**
   * Constructor
   * @param model The model
   */
  public SplashController(SplashModel model )
  {
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
