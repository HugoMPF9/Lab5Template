package pt.pa.refactoring.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReviewTest {
    Review review1;

    @BeforeEach
    public void setUp() {
        review1 = new Review("TestPerson", "TestText", 4);
    }

    @Test
    void getReviewer() {
        assertEquals("TestPerson", review1.getReviewer());
    }

    @Test
    void getText() {
        assertEquals("TestText", review1.getText());
    }

    @Test
    void getRating() {
        assertEquals(4, review1.getRating(), 0);
    }
}