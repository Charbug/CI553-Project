package clients;

import clients.backDoor.BackDoorController;
import clients.backDoor.BackDoorModel;
import clients.backDoor.BackDoorView;
import clients.cashier.CashierController;
import clients.cashier.CashierModel;
import clients.cashier.CashierView;
import clients.customer.CustomerController;
import clients.customer.CustomerModel;
import clients.customer.CustomerView;
import clients.packing.PackingController;
import clients.packing.PackingModel;
import clients.packing.PackingView;
import clients.splash.SplashController;
import clients.splash.SplashModel;
import clients.splash.SplashView;
import middle.LocalMiddleFactory;
import middle.MiddleFactory;
import middle.StockException;

import javax.swing.*;
import java.awt.*;

/**
 * Starts all the clients (user interface)  as a single application.
 * Good for testing the system using a single application.
 *
 * @author Mike Smith University of Brighton
 * @author Shine University of Brighton
 * @version year-2024
 */

class Main {
    public static void main(String[] args) throws StockException {
        new Main().begin();
    }

    /**
     * Starts the system (Non-distributed)
     */
    public void begin() throws StockException {
        //DEBUG.set(true); /* Lots of debug info */
        MiddleFactory mlf = new LocalMiddleFactory();  // Direct access
        startSplashGUI_MVC(mlf);
        startCustomerGUI_MVC(mlf);
        startCashierGUI_MVC(mlf);
        startPackingGUI_MVC(mlf);
        startBackDoorGUI_MVC(mlf);
    }

    /**
     * start the Customer client, -search product
     *
     * @param mlf A factory to create objects to access the stock list
     */
    public void startCustomerGUI_MVC(MiddleFactory mlf) throws StockException {
        JFrame window = new JFrame();
        window.setTitle("Customer Client MVC");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension pos = PosOnScrn.getPos();

        CustomerModel model = new CustomerModel(mlf);
        CustomerView view = new CustomerView(window, mlf, pos.width, pos.height);
        CustomerController cont = new CustomerController(model, view);
        view.setController(cont);


        model.addObserver(view);       // Add observer to the model, ---view is observer, model is Observable
        window.setVisible(false);         // Make window visible
        mlf.addFrame("customerFrame", window);  // Adds frame to Middle Factory Hash Map for navigation
    }

    /**
     * start the cashier client - customer check stock, buy product
     *
     * @param mlf A factory to create objects to access the stock list
     */
    public void startCashierGUI_MVC(MiddleFactory mlf) throws StockException {
        JFrame window = new JFrame();
        window.setTitle("Cashier Client MVC");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension pos = PosOnScrn.getPos();

        CashierModel model = new CashierModel(mlf);
        CashierView view = new CashierView(window, mlf, pos.width, pos.height);
        CashierController cont = new CashierController(model, view);
        view.setController(cont);


        model.addObserver(view);       // Add observer to the model
        window.setVisible(false);         // Make window visible
        mlf.addFrame("cashierFrame", window);  // Adds frame to Middle Factory Hash Map for navigation
        model.askForUpdate();            // Initial display
    }

    /**
     * start the Packing client - for warehouse staff to pack the bought order for customer, one order at a time
     *
     * @param mlf A factory to create objects to access the stock list
     */

    public void startPackingGUI_MVC(MiddleFactory mlf) {
        JFrame window = new JFrame();
        window.setVisible(false);         // Make window visible
        window.setTitle("Packing Client MVC");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension pos = PosOnScrn.getPos();

        PackingModel model = new PackingModel(mlf);
        PackingView view = new PackingView(window, mlf, pos.width, pos.height);
        PackingController cont = new PackingController(model, view);
        view.setController(cont);

        model.addObserver(view);       // Add observer to the model
        window.setVisible(false);         // Make window visible
        mlf.addFrame("packingFrame", window);  // Adds frame to Middle Factory Hash Map for navigation
    }

    /**
     * start the BackDoor client - store staff to check and update stock
     *
     * @param mlf A factory to create objects to access the stock list
     */
    public void startBackDoorGUI_MVC(MiddleFactory mlf) throws StockException {
        JFrame window = new JFrame();

        window.setTitle("BackDoor Client MVC");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension pos = PosOnScrn.getPos();

        BackDoorModel model = new BackDoorModel(mlf);
        BackDoorView view = new BackDoorView(window, mlf, pos.width, pos.height);
        BackDoorController cont = new BackDoorController(model, view);
        view.setController(cont);

        model.addObserver(view);       // Add observer to the model
        window.setVisible(false);         // Make window visible
        mlf.addFrame("backdoorFrame", window);  // Adds frame to Middle Factory Hash Map for navigation
    }

    /**
     * start the SplashScreen client - Home screen to navigate to the other screens
     *
     * @param mlf A factory to create objects to access the stock list
     */
    public void startSplashGUI_MVC(MiddleFactory mlf) {
        JFrame window = new JFrame();
        window.setTitle("Splash Screen MVC");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension pos = PosOnScrn.getPos();

        SplashModel model = new SplashModel(mlf);
        SplashView view = new SplashView(window, pos.width, pos.height);
        SplashController cont = new SplashController(model);
        view.setController(cont);

        model.addObserver(view);       // Add observer to the model
        window.setVisible(true);         // Make window visible
        mlf.addFrame("splashFrame", window);  // Adds frame to Middle Factory Hash Map for navigation
    }



}
