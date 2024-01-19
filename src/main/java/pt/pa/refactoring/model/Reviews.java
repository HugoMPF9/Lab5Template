package pt.pa.refactoring.model;

import java.util.ArrayList;

/**
 * @author amfs
 */
public class Reviews {
    private static final double INITIAL_SUM = 0.0;
    private ArrayList<Review> reviews;

    public Reviews() {
        reviews = new ArrayList<>();
    }

    public double getAvgRating() {
        double sum = INITIAL_SUM;
        for (Review r : reviews) {
            sum += r.getRating();
        }
        return sum / reviews.size();
    }

    public int getTotal() {
        return reviews.size();
    }

    public void add(Review review) {
        reviews.add(review);
    }

    // other methods that are relevant for the Reviews class
}
