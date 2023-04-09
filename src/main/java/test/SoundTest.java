package test;

// Import classes used.
import org.Snake.Sound;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.Clip;

import static org.junit.jupiter.api.Assertions.*;


/**
 * The purpose of the SoundTest class is to test out the methods and functionality
 * of the Sound class.
 *
 *
 * @author adams
 */
class SoundTest {

    // Instantiating the clip variable.
    private Clip clip;

    //Sets the clip to null before each test.
    @BeforeEach
    void setUp() {
        clip = null;
    }

    /**
     * The after each checks to see if the clip is null or not.
     * If the clip is not null we stop it and close the clip.
     */
    @AfterEach
    void tearDown() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }

    /**
     * The test plaaysound method tests playing sound in the sound class.
     * We just try to play sound and check that clip is not null
     * and that the clip is running.
     *
     * We then have a catch statement with an exception incase we are unable to play sound.
     */
    @Test
    @DisplayName("Test playSound method")
    void testPlaySound() {
        try {
            Sound.playSound();
            clip = Sound.clip;
            assertNotNull(clip);
            assertTrue(clip.isRunning());
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    /**
     * The test reset sound method tests out the reset method in Sound.
     *
     * We start by setting up the sound and playing sound and verifying that it is not null.
     *
     * We then call reset sound and check that it has succesfully reset.
     *
     * We then have a catch incase the test fails.
     */
    @Test
    @DisplayName("Test resetSound method")
    void testResetSound() {
        try {
            Sound.playSound();
            clip = Sound.clip;
            assertNotNull(clip);
            clip.start();


            Sound.resetSound();

            assertTrue(clip.isRunning());
            assertEquals(0, clip.getFramePosition());
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
}
