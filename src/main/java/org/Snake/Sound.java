package org.Snake;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;


public class Sound {
    public static Clip clip;

    public Sound(Clip clip) {
    this.clip = clip;



    }

    /**
     * Plays the sound file
     */
    public static void playSound() {
        try {
            // load the sound file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream
                    (new File("src/main/SoundTrack/506893__mrthenoronha__upbeat-theme-loop.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // set loop count to infinite
            clip.start();
        } catch (Exception e) {
            JOptionPane.showMessageDialog
                    (null, "Error playing sound: " + e.getMessage());
        }
    }


    /**
     * Stops the sound file
     */
    public static void resetSound() {
        if (clip.isRunning()) {
            clip.stop(); // stop the current clip if it is running
        }
        clip.setFramePosition(0); // reset the clip to its beginning
        clip.start(); // start playing the clip again

        if (clip != null) {

            clip.setFramePosition(0);
            clip.start();
        }
    }
}
