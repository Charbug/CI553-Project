package clients.backDoor;

import middle.MiddleFactory;
import middle.StockException;
import middle.StockReadWriter;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Implements the Customer view.
 */

public class BackDoorView implements Observer
{
  private static final String RESTOCK  = "Add";
  private static final String CLEAR    = "Clear";
  private static final String QUERY    = "Query";
  private static final String RETURN    = "Return";


  private static final int H = 300;       // Height of window pixels
  private static final int W = 400;       // Width  of window pixels

  private final JLabel      pageTitle  = new JLabel();
  private final JLabel      theAction  = new JLabel();
  private final JComboBox<String> theComboBox = new JComboBox<>();
  SpinnerModel spinnerValues = new SpinnerNumberModel(1, 0, 99, 1);
  private final JSpinner  theInputNo = new JSpinner(spinnerValues);
  private final JTextArea   theOutput  = new JTextArea();
  private final JScrollPane theSP      = new JScrollPane();
  private final JButton     theBtClear = new JButton( CLEAR );
  private final JButton     theBtRStock = new JButton( RESTOCK );
  private final JButton     theBtQuery = new JButton( QUERY );
  private final JButton     theBtReturn = new JButton( RETURN );


  private StockReadWriter theStock     = null;
  private BackDoorController cont= null;

  /**
   * Construct the view
   * @param rpc   Window in which to construct
   * @param mf    Factor to deliver order and stock objects
   * @param x     x-cordinate of position of window on screen 
   * @param y     y-cordinate of position of window on screen  
   */
  public BackDoorView(  RootPaneContainer rpc, MiddleFactory mf, int x, int y )
  {
    try                                             // 
    {      
      theStock = mf.makeStockReadWriter();          // Database access
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
    pageTitle.setText( "Staff check and manage stock" );                        
    cp.add( pageTitle );
    
    theBtQuery.setBounds( 16, 25+60*0, 80, 40 );    // Buy button 
    theBtQuery.addActionListener(                   // Call back code
      e -> cont.doQuery((String) theComboBox.getSelectedItem()) );
    cp.add( theBtQuery );                           //  Add to canvas

    theBtRStock.setBounds( 16, 25+60*1, 80, 40 );   // Check Button
    theBtRStock.addActionListener(                  // Call back code
      e -> cont.doRStock((String) theComboBox.getSelectedItem(), theInputNo.getValue()) );
    cp.add( theBtRStock );                          //  Add to canvas

    theBtClear.setBounds( 16, 25+60*2, 80, 40 );    // Buy button 
    theBtClear.addActionListener(                   // Call back code
      e -> cont.doClear() );
    cp.add( theBtClear );                           //  Add to canvas

    theBtReturn.setBounds( 16, 25+60*3, 80, 40 );    // Buy button
    theBtReturn.addActionListener(                   // Call back code
            e -> cont.returnButton() );
    cp.add( theBtReturn );                           //  Add to canvas


    theAction.setBounds( 110, 25 , 270, 20 );       // Message area
    theAction.setText( "" );                        // Blank
    cp.add( theAction );                            //  Add to canvas

    theComboBox.setBounds( 110, 50, 120, 40 );         // Input Area
    cp.add(theComboBox);                             //  Add to canvas


    theInputNo.setBounds( 260, 50, 120, 40 );       // Input Area
    cp.add( theInputNo );                           //  Add to canvas

    theSP.setBounds( 110, 100, 270, 160 );          // Scrolling pane
    theOutput.setText( "" );                        //  Blank
    theOutput.setFont( f );                         //  Uses font  
    cp.add( theSP );                                //  Add to canvas
    theSP.getViewport().add( theOutput );           //  In TextArea
    rootWindow.setVisible( true );                  // Make visible
    theComboBox.requestFocus();                        // Focus is here
  }
  
  public void setController( BackDoorController c ) throws StockException {
    cont = c;
    cont.populateComboBox();
  }

  /**
   * Update the view, called by notifyObservers(theAction) in model,
   * @param modelC   The observed model
   * @param arg      Specific args 
   */
  @Override
  public void update( Observable modelC, Object arg )  
  {
    BackDoorModel model  = (BackDoorModel) modelC;
    String        message = (String) arg;
    theAction.setText( message );
    
    theOutput.setText( model.getBasket().getDetails() );
    theComboBox.requestFocus();
  }

  public void populateComboBox(String[] list) {
    for (String id : list) {
      theComboBox.addItem( id );
    }
  }

  public void clearView() {
    theComboBox.setSelectedIndex( 0 );
    int defaultValue = 1;
    theInputNo.setValue(defaultValue);
  }
}

