package clients;
import java.awt.*;

/**
 * Returns a position for the client window on the screen
 * The clients are assumed to be all the same size 400x300
 * @author Mike Smith University of Brighton
 * @version 1.0
 */
class PosOnScrn
{
  private final static int clientW = 400;
  private final static int clientH = 300;
  
  private static final int maxX;  // Width of screen
  private static final int maxY;  // Height of screen

    // class initialiser
  //  Will be called (once) when the class is loaded
  static
  {
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    maxX = (int) dimension.getWidth();
    maxY = (int) dimension.getHeight();
  }

    /**
   * return position for new window on screen
   *  slight misuse of the inbuilt Dimension class
   *  as used to hold an x,y co-ordinate pair
   * @return position for new window
   */
  public static Dimension getPos()
  {
      // Initial window pos on screen
      int cY = 200;
      // Initial window pos on screen
      int cX = 500;
      return new Dimension(cX, cY);
  }
}
