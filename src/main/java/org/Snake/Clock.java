package org.Snake;

import java.time.Duration;
import java.time.Instant;

/**
 * Clock class which is meant to track passing time
 * @author
 * @version
 */
public class Clock {

  private int frames;
  private static float framesPerClockTick;

  private int lastFrames;

  Instant prev;

  Instant current;

  double tickTime;
  private static float frameRate;



  /**
   * Clock constructor which sets the time to the current time
   */
  public Clock() {
    prev = Instant.now();
    tickTime = 100;
  }

  /**
   * Getter for the frames per second
   */
  public static float getFramesPerSecond() {
    return frameRate;
  }

  /**
   * Tick method which checks if the time has passed the tick time
   * @return boolean
   */
  public boolean tick() {
    frames++;
    current = Instant.now();
    Duration timeElapsed = Duration.between(prev, current);
    framesPerClockTick = (float) (frames/(timeElapsed.toMillis()/tickTime));
    frameRate = (float) (framesPerClockTick * 1000 / tickTime);
    //System.out.println(abs(timeElapsed.toMillis()));
    if (timeElapsed.toMillis() > tickTime) {
      prev = Instant.now();
      resetFrames();
      return true;
    }
    return false;
  }
  public static float getFramesPerClock() {
    return framesPerClockTick;
  }

  /**
   * Resets the frames
   */
  private void resetFrames() {
    frames = 0;
  }

  /**
   * Resets the clock
   */
  public void reset() {
    prev = Instant.now();
    this.resetFrames();
  }
}
