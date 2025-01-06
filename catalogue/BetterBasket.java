package catalogue;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;

/**
 * Write a description of class BetterBasket here.
 *
 * @author Anon
 * @version 1.0
 */
public class BetterBasket extends Basket implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public boolean add(Product pr) {
        boolean newProductFlag = true;  /* flag for if item already exists in list, set false when item is found */

        for (Object i : super.toArray()) {  /* Casting Basket to Array to iterate */
            Product current = (Product) i;  /* Casting current Object back to Product to access its function */

            /* If product exists, flip newProduct flag and increment existing product */
            if (current.getProductNum().equals(pr.getProductNum())) {
                newProductFlag = false;
                current.setQuantity(current.getQuantity() + 1);
            }
        }
        /* Adds item to list if it doesn't exist, else returns false */
        if (newProductFlag) {
            return super.add(pr);
        } else {
            return false;
        }
    }

    /* Sorts item in basket display */
    public void sortBasket() {

        /* Creating Comparator class for Product items */
        class BasketComparator implements Comparator<Product> {
            public int compare(Product a, Product b) {
                int intA = Integer.parseInt(a.getProductNum());
                int intB = Integer.parseInt(b.getProductNum());
                /* Gets int value of product codes and compares using Integer compare method */
                return Integer.compare(intA, intB);
            }
        }

        this.sort(new BasketComparator());  /* Uses ArrayList sort method to sort by values generated from constructor */
    }

    @Override /* Overrides getDetails function to sort list before generation */
    public String getDetails() {
        sortBasket();
        return super.getDetails();
    }
}
