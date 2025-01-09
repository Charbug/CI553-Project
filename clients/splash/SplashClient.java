package clients.splash;

import middle.MiddleFactory;
import middle.Names;
import middle.RemoteMiddleFactory;
import middle.StockException;

import javax.swing.*;

/**
 * The standalone Cashier Client.
 */


public class SplashClient
{
    /**
     *  Splash Client
     */
   public static void main (String[] args)
   {
     String stockURL = args.length < 1     // URL of stock RW
                     ? Names.STOCK_RW      //  default  location
                     : args[0];            //  supplied location
     String orderURL = args.length < 2     // URL of order
                     ? Names.ORDER         //  default  location
                     : args[1];            //  supplied location
     
    RemoteMiddleFactory mrf = new RemoteMiddleFactory();
    mrf.setStockRWInfo( stockURL );
    mrf.setOrderInfo  ( orderURL );        //
    displayGUI(mrf);                       // Create GUI
  }


  private static void displayGUI(MiddleFactory mf)
  {     
    JFrame  window = new JFrame();
     
    window.setTitle( "Cashier Client (MVC RMI)");
    window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    
    SplashModel model = new SplashModel(mf);
    SplashView view  = new SplashView( window, 0, 0 );
    SplashController cont  = new SplashController( model);
    view.setController( cont );

    model.addObserver( view );       // Add observer to the model
    window.setVisible(true);         // Display Screen
  }
}
