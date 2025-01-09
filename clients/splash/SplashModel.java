package clients.splash;

import debug.DEBUG;
import middle.*;

import java.util.Observable;

/**
 * Implements the Model of the cashier client
 */
public class SplashModel extends Observable {
    public void askForUpdate() {
    }

    private MiddleFactory midFact = null; // Storing for navigation between screens

    /**
     * Construct the model of the Cashier
     *
     * @param mf The factory to create the connection objects
     */

    public SplashModel(MiddleFactory mf) {
        try                                           //
        {
            mf.makeStockReadWriter();// Database access
            mf.makeOrderProcessing();// Process order
            midFact = mf;

        } catch (Exception e) {
            DEBUG.error("CashierModel.constructor\n%s", e.getMessage());
        }
        // Current state
        // Current state
    }

    /**
     * Navigates to Cashier Screen
     */

    public void loadCashier() {
        midFact.getFrame("splashFrame").setVisible(false);
        midFact.getFrame("cashierFrame").setVisible(true);
    }

    /**
     * Navigates to Customer Screen
     */

    public void loadCustomer()  {
        midFact.getFrame("splashFrame").setVisible(false);
        midFact.getFrame("customerFrame").setVisible(true);
    }

    /**
     * Navigates to Packing Screen
     */

    public void loadPacking()  {
        midFact.getFrame("splashFrame").setVisible(false);
        midFact.getFrame("packingFrame").setVisible(true);
    }

    /**
     * Navigates to Bac Screen
     */

    public void loadBackdoor()  {
        midFact.getFrame("splashFrame").setVisible(false);
        midFact.getFrame("backdoorFrame").setVisible(true);
    }
}
  
