package dbAccess;

/**
  * Implements generic management of a database.
  * @author  Mike Smith University of Brighton
  * @version 2.0
  */
 
/**
 * Base class that defines the access to the database driver
 */
public class DBAccess
{

  /**
   * Empty
   */

  public void loadDriver() throws Exception
  {
    throw new RuntimeException("No driver");
  }

  /**
   * Empty
   */

  public String urlOfDatabase()
  {
    return "";
  }

  /**
   * Empty
   */

  public String username()
  {
    return "";
  }

  /**
   * Empty
   */

  public String password()
  {
    return "";
  }
}
