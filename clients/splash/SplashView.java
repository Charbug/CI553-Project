package clients.splash;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;


/**
 * View of the model 
 */
public class SplashView implements Observer
{
  private static final int H = 300;       // Height of window pixels
  private static final int W = 400;       // Width  of window pixels

  private static final String CUSTOMER = "Customer";
  private static final String CASHIER = "Cashier";
  private static final String PACKING = "Packing";
  private static final String BACKDOOR    = "Backdoor";

    private SplashController cont       = null;
  
  /**
   * Construct the view
   * @param rpc   Window in which to construct
   * @param x     x-coordinate of position of window on screen 
   * @param y     y-coordinate of position of window on screen  
   */
          
  public SplashView(RootPaneContainer rpc, int x, int y  )
    {
    Container cp         = rpc.getContentPane();    // Content Pane
    Container rootWindow = (Container) rpc;         // Root Window
    cp.setLayout(null);                             // No layout manager
    rootWindow.setSize( W, H );                     // Size of Window
    rootWindow.setLocation( x, y );

      // Font f is

      JLabel pageTitle = new JLabel();
      pageTitle.setBounds( 110, 0 , 270, 20 );
    pageTitle.setText( "Ministore Navigation Screen" );
    cp.add(pageTitle);

      JButton theBtCustomer = new JButton(CUSTOMER);
      theBtCustomer.setBounds( 50, 50, 80, 40 );    // Check Button
    theBtCustomer.addActionListener(                   // Call back code
      e -> cont.loadCustomer());
    cp.add(theBtCustomer);                           //  Add to canvas

      JButton theBtCashier = new JButton(CASHIER);
      theBtCashier.setBounds( 250, 50, 80, 40 );      // Buy button
    theBtCashier.addActionListener(                     // Call back code
      e -> cont.loadCashier());
    cp.add(theBtCashier);                             //  Add to canvas

      JButton theBtPacking = new JButton(PACKING);
      theBtPacking.setBounds( 50, 200, 80, 40 );   // Bought Button
    theBtPacking.addActionListener(                  // Call back code
            e -> cont.loadPacking() );
    cp.add(theBtPacking);                          //  Add to canvas

      JButton theBtBackdoor = new JButton(BACKDOOR);
      theBtBackdoor.setBounds( 250, 200, 80, 40 );   // Bought Button
    theBtBackdoor.addActionListener(                  // Call back code
            e -> cont.loadBackdoor() );
    cp.add(theBtBackdoor);                          //  Add to canvas

    rootWindow.setVisible( true );                  // Make visible
  }

  /**
   * The controller object, used so that an interaction can be passed to the controller
   * @param c   The controller
   */

  public void setController( SplashController c )
  {
    cont = c;
  }

  /**
   * Update the view
   * @param modelC   The observed model
   * @param arg      Specific args
   */
  @Override
  public void update( Observable modelC, Object arg )
  {
  }
}
