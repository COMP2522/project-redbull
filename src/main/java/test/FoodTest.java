package test;


/**
 * Imports for the tests.
 */

import org.Snake.Food;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The purpose of FoodTest is to test the methods in Food.
 *
 * @author adams
 */
@DisplayName("Food class tests")
class FoodTest {

    // Instantiating the food variable.
    private Food food;

    // Set up before every test is run.
    // Makes a new food.
    @BeforeEach
    void setUp() {
        food = new Food(10, 20, 30, "food");
    }

    // Tests the eaten method in the food class.
    @Test
    @DisplayName("Test isEaten() method")
    void testIsEaten() {
        assertFalse(food.isEaten(), "The initial state of isEaten should be false");
    }


    // Tests out the eat method in the food class.
    @Test
    @DisplayName("Test eat() method")
    void testEat() {
        food.eat();
        assertTrue(food.isEaten(), "The state of isEaten should be true after calling eat()");
    }


    // Tests out the reset method in the food class.
    @Test
    @DisplayName("Test reset() method")
    void testReset() {
        food.eat();
        food.reset();
        assertFalse(food.isEaten(), "The state of isEaten should be false after calling reset()");
    }
}