package test;

import static org.junit.jupiter.api.Assertions.*;

import org.Snake.UI.UIComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import processing.core.PApplet;


/**
 * The UIComponentTest is a junit 5 test class that tests out the UIComponent class.
 */
@DisplayName("UIComponent Test")
class UIComponentTest {

    // Initializes parent
    private PApplet parent;

    // Initializes x
    private float x;

    // Initializes y
    private float y;

    // Initializes the width
    private float width;


    // Initializes height
    private float height;

    // Initializes uiComponent
    private UIComponent uiComponent;

    // Sets up a uiComponent before each test and sets up variables.
    @BeforeEach
    void setUp() {
        parent = new PApplet();
        x = 100f;
        y = 200f;
        width = 50f;
        height = 30f;
        uiComponent = new UIComponent(parent, x, y, width, height) {

            @Override
            public void draw() {
            }

            @Override
            public void mouseClicked(float mx, float my) {
            }
        };
    }

    // Testing that the test is true when contains is given dimensions that fit.
    @Test
    @DisplayName("Test contains() method with point inside component")
    void testContainsWithPointInside() {
        float mx = x + 10f;
        float my = y + 5f;
        boolean contains = uiComponent.contains(mx, my);
        assertTrue(contains);
    }


    // Testing that the test is false when contains is given dimensions that are out of bounds.
    @Test
    @DisplayName("Test contains() method with point outside component")
    void testContainsWithPointOutside() {
        float mx = x - 10f;
        float my = y - 5f;
        boolean contains = uiComponent.contains(mx, my);
        assertFalse(contains);
    }


    // Tests that GetWidth gets the correct width.
    @Test
    @DisplayName("Test getWidth() method")
    void testGetWidth() {
        assertEquals(width, uiComponent.getWidth());
    }

    // Tests that GetHeight gets the correct height.
    @Test
    @DisplayName("Test getHeight() method")
    void testGetHeight() {
        assertEquals(height, uiComponent.getHeight());
    }

    //Tests that GetX gets the correct x value.
    @Test
    @DisplayName("Test getX() method")
    void testGetX() {
        assertEquals((int) x, uiComponent.getX());
    }

    // Tests that GetY gets the correct y value.
    @Test
    @DisplayName("Test getY() method")
    void testGetY() {
        assertEquals((int) y, uiComponent.getY());
    }


}
