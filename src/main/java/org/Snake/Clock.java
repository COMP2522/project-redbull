package org.Snake;

import java.time.Duration;
import java.time.Instant;

/**
 * Clock class which is meant to track passing time
 * @author
 * @version 1.0
 */
public class Clock {

  private int frames;
  private static float framesPerClockTick;

  private int lastFrames;

  private Instant prev;

  private Instant current;

  private double tickTime;
  private static float frameRate;



  /**
   * Clock constructor which sets the time to the current time
   */
  public Clock() {
    setPrev(Instant.now());
    setTickTime(100);
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
    setCurrent(Instant.now());
    Duration timeElapsed = Duration.between(getPrev(), getCurrent());
    framesPerClockTick = (float) (frames/(timeElapsed.toMillis()/ getTickTime()));
    frameRate = (float) (framesPerClockTick * 1000 / getTickTime());
    //System.out.println(abs(timeElapsed.toMillis()));
    if (timeElapsed.toMillis() > getTickTime()) {
      setPrev(Instant.now());
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
    setPrev(Instant.now());
    this.resetFrames();
  }

  public Instant getPrev() {
    return prev;
  }

  public void setPrev(Instant prev) {
    this.prev = prev;
  }

  public Instant getCurrent() {
    return current;
  }

  public void setCurrent(Instant current) {
    this.current = current;
  }

  public double getTickTime() {
    return tickTime;
  }

  public void setTickTime(double tickTime) {
    this.tickTime = tickTime;
  }
}
