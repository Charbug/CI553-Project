package catalogue;

import java.io.Serializable;

/**
 * Used to hold the following information about
 * a product: Product number, Description, Price, Stock level.
 *
 * @author Mike Smith University of Brighton
 * @version 2.0
 */

public class Product implements Serializable {
    private static final long serialVersionUID = 20092506;
    private String theProductNum;       // Product number
    private String theDescription;      // Description of product
    private double thePrice;            // Price of product
    private int theQuantity;         // Quantity involved

    /**
     * Construct a product details
     *
     * @param aProductNum  Product number
     * @param aDescription Description of product
     * @param aPrice       The price of the product
     * @param aQuantity    The Quantity of the product involved
     */
    public Product(String aProductNum, String aDescription,
                   double aPrice, int aQuantity) {
        theProductNum = aProductNum;     // Product number
        theDescription = aDescription;    // Description of product
        thePrice = aPrice;          // Price of product
        theQuantity = aQuantity;       // Quantity involved
    }

    /**
     *  Gets Product Number
     * @return ProductNumber [String]
     */

    public String getProductNum() {
        return theProductNum;
    }

    /**
     *  Gets description
     * @return Description [String]
     */

    public String getDescription() {
        return theDescription;
    }

    /**
     *  Gets price
     * @return Price [double]
     */

    public double getPrice() {
        return thePrice;
    }

    /**
     *  Gets quantity
     * @return Quantity [int]
     */

    public int getQuantity() {
        return theQuantity;
    }

    /**
     *  Sets Product Number
     * @param  aProductNum Product Number [String]
     */

    public void setProductNum(String aProductNum) {
        theProductNum = aProductNum;
    }

    /**
     *  Sets Description
     * @param  aDescription Description [String]
     */

    public void setDescription(String aDescription) {
        theDescription = aDescription;
    }

    /**
     *  Sets Price
     * @param  aPrice Price [Double]
     */

    public void setPrice(double aPrice) {
        thePrice = aPrice;
    }

    /**
     *  Sets Quantity
     * @param  aQuantity Quantity [int]
     */

    public void setQuantity(int aQuantity) {
        theQuantity = aQuantity;
    }

}
