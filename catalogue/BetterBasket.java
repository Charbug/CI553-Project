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
        boolean newProductFlag = true;  /* flag for if item already exists in list, set false when item is found */

        for (Object i: super.toArray()) {  /* Casting Basket to Array to iterate */
            Product current = (Product) i;  /* Casting current Object back to Product to access its function */

            /* If product exists, flip newProduct flag and increment existing product */
            if (current.getProductNum().equals(pr.getProductNum())) {
                newProductFlag = false;
                current.setQuantity(current.getQuantity() + 1);
            }
        }
        /* Adds item to list if it doesn't exist, else returns false */
        if (newProductFlag) {return super.add(pr);} else {return false;}
    }


}
