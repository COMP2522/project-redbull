package test;


import org.Snake.Window;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static processing.core.PApplet.min;

/**
 * JUnit 5 test class for the Window class
 */
@DisplayName("Window class tests")
class WindowTest {

    // Initializes window.
    private Window window;

    //Gets the screen size of the user.
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // Sets up a new window before each test.
    @BeforeEach
    void setUp() {
        window = new Window();
    }

    // Tests if window gets the correct width depending on the users monitor screen size.
    @Test
    @DisplayName("Test getWidth method")
    void testGetWidth() {

        int expected = min((int) (screenSize.getWidth()*0.99), (int) (screenSize.getHeight()*0.99));
        int actual = window.getWidth();
        assertEquals(expected, actual);
    }


    // Gets the Offset depending on the users screen size.
    @Test
    @DisplayName("Test getOffset method")
    void testGetOffset() {

        int width = min((int) (screenSize.getWidth() * 0.99), (int) (screenSize.getHeight() * 0.99));
        int expected = (int) ((screenSize.getWidth()-width)/(2));
        int actual = window.getOffset();
        assertEquals(expected, actual);
    }



}
