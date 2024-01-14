package pt.pa.refactoring.model;

import java.util.ArrayList;

/**
 * @author amfs
 */
public class Reviews extends ArrayList<Review> {
    public int getTotal() {
        return size();
    }

    public double getAvgRating() {
        double sum = 0.0;
        for (Review r : this) {
            sum += r.getRating();
        }
        return sum / getTotal();
    }
}
