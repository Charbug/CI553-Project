package clients.customer;

import catalogue.Basket;
import catalogue.BetterBasket;
import clients.Picture;
import middle.MiddleFactory;
import middle.StockException;
import middle.StockReader;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

/**
 * Implements the Customer view.
 */

public class CustomerView implements Observer
{
  class Name                              // Names of buttons
  {
    public static final String CHECK  = "Check";
    public static final String CLEAR  = "Clear";
    public static final String RETURN = "Return";
  }

  private static final int H = 300;       // Height of window pixels
  private static final int W = 400;       // Width  of window pixels

  private final JLabel      pageTitle  = new JLabel();
  private final JLabel      theAction  = new JLabel();
  private final JComboBox<String>  theComboBox   = new JComboBox<>();
  private final JTextArea   theOutput  = new JTextArea();
  private final JScrollPane theSP      = new JScrollPane();
  private final JButton     theBtCheck = new JButton( Name.CHECK );
  private final JButton     theBtClear = new JButton( Name.CLEAR );
  private final JButton     theBtReturn = new JButton( Name.RETURN );

  private Picture thePicture = new Picture(80,80);
  private StockReader theStock   = null;
  private CustomerController cont= null;

  /**
   * Construct the view
   * @param rpc   Window in which to construct
   * @param mf    Factor to deliver order and stock objects
   * @param x     x-cordinate of position of window on screen 
   * @param y     y-cordinate of position of window on screen  
   */
  
  public CustomerView( RootPaneContainer rpc, MiddleFactory mf, int x, int y )
  {
    try                                             // 
    {      
      theStock  = mf.makeStockReader();             // Database Access
    } catch ( Exception e )
    {
      System.out.println("Exception: " + e.getMessage() );
    }
    Container cp         = rpc.getContentPane();    // Content Pane
    Container rootWindow = (Container) rpc;         // Root Window
    cp.setLayout(null);                             // No layout manager
    rootWindow.setSize( W, H );                     // Size of Window
    rootWindow.setLocation( x, y );

    Font f = new Font("Monospaced",Font.PLAIN,12);  // Font f is
    
    pageTitle.setBounds( 110, 0 , 270, 20 );       
    pageTitle.setText( "Search products" );                        
    cp.add( pageTitle );

    theBtCheck.setBounds( 16, 20, 80, 40 );    // Check button
    theBtCheck.addActionListener(                   // Call back code
      e -> cont.doCheck( Objects.requireNonNull(theComboBox.getSelectedItem()).toString() ) );
    cp.add( theBtCheck );                           //  Add to canvas

    theBtClear.setBounds( 16, 70, 80, 40 );    // Clear button
    theBtClear.addActionListener(                   // Call back code
      e -> cont.doClear() );
    cp.add( theBtClear );                           //  Add to canvas

    thePicture.setBounds( 16, 120, 80, 80 );   // Picture area
    cp.add( thePicture );                           //  Add to canvas
    thePicture.clear();

    theBtReturn.setBounds( 16, 210, 80, 40 );    // Clear button
    theBtReturn.addActionListener(                   // Call back code
            e -> cont.returnButton() );
    cp.add( theBtReturn );                           //  Add to canvas

    theAction.setBounds( 110, 25 , 270, 20 );       // Message area
    theAction.setText( " " );                       // blank
    cp.add( theAction );                            //  Add to canvas

    theComboBox.setBounds( 110, 50, 270, 40 );         // Product no area
    cp.add( theComboBox );                             //  Add to canvas
    
    theSP.setBounds( 110, 100, 270, 160 );          // Scrolling pane
    theOutput.setText( "" );                        //  Blank
    theOutput.setFont( f );                         //  Uses font
    cp.add( theSP );                                //  Add to canvas
    theSP.getViewport().add( theOutput );           //  In TextArea


    rootWindow.setVisible( true );                  // Make visible);
  }

  /**
   * The controller object, used so that an interaction can be passed to the controller
   * Populate Combobox is called here as it has to be invoked after the view and model are linked.
   * @param c   The controller
   */

  public void setController( CustomerController c ) throws StockException {
    cont = c;
    cont.populateComboBox();
  }

  /**
   * Update the view
   * @param modelC   The observed model
   * @param arg      Specific args 
   */
   
  public void update( Observable modelC, Object arg )
  {
    CustomerModel model  = (CustomerModel) modelC;
    String        message = (String) arg;
    theAction.setText( message );
    ImageIcon image = model.getPicture();  // Image of product
    if ( image == null )
    {
      thePicture.clear();                  // Clear picture
    } else {
      thePicture.set( image );             // Display picture
    }
    theOutput.setText( model.getBasket().getDetails() );
  }

  /**
   * Populate the combobox with IDs
   * @param list   List of ID strings
   */

  public void populateComboBox(String[] list) {
    for (String id : list) {
      theComboBox.addItem( id );
    }
  }

  /**
   * Resets Combo box value to the lowest index
   */
  
  public void doClear() {
    theComboBox.setSelectedIndex(0);
  }
}
