package test;
import org.example.Clock;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * The purpose of the clockTest class is to test the functionality of the Clock class.
 */
public class ClockTest {

    // Creating a private clock for the clockTest class.
    private Clock clock;


    // Creates a new Clock before all new tests
    @BeforeEach
    void setUp() {
        clock = new Clock();
    }



    // Tests out the functionality of the clocks reset ability.
    @Test
    void testReset() {


        clock.reset();

        assertFalse(clock.tick());
    }


    // Ensures that the clock succesfully gets the frames per second
    @Test
    void testGetFramesPerSecond() {


        float fps = Clock.getFramesPerSecond();

        // Frames per second should be greater than 0
        assertTrue(fps > 0);
    }


    // Ensures that the clock gets the frames per clock
    @Test
    void testGetFramesPerClock() {


        float fpc = Clock.getFramesPerClock();

        // Frames per clock should be greater than 0
        assertTrue(fpc > 0);
    }

}
