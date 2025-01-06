package catalogue;

import java.io.Serializable;
import java.util.Collections;

/**
 * Write a description of class BetterBasket here.
 *
 * @author Your Name
 * @version 1.0
 */
public class BetterBasket extends Basket implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public boolean add(Product pr) {
        boolean newProductFlag = true;

        for (Object i: super.toArray()) {
            Product current = (Product) i;
            if (current.getProductNum().equals(pr.getProductNum())) {
                newProductFlag = false;
                current.setQuantity(current.getQuantity() + 1);
            }
        }
        if (newProductFlag) {return super.add(pr);} else {return false;}
    }

    // You need to add code here
    // merge the items for same product,
    // or sort the item based on the product number
}
