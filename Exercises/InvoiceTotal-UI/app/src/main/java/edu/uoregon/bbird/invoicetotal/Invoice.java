package edu.uoregon.bbird.invoicetotal;

/**
 * Created by Brian Bird on 6/21/16.
 */
public class Invoice {
    double discountPercent = 0.0;
    double discountAmount = 0.0;

    public double calcTotal(double subtotal) {
        if (subtotal >= 200.0)
            discountPercent = 0.2;     // 20% discount
        else if (subtotal >= 100.0)
            discountPercent = 0.1;     // 10% discount

        discountAmount = subtotal * discountPercent;

        return subtotal - discountAmount;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }
}
