package org.example;

import java.time.Duration;
import java.time.Instant;

//Clock class which is meant to track passing time
public class Clock {

  private int frames;
  private static float framesPerClockTick;

  private int lastFrames;

  Instant prev;

    Instant current;

    double tickTime;
  private static float frameRate;

  //Public clock constructor
    public Clock() {
      prev = Instant.now();
      tickTime = 100;
    }

  public static float getFramesPerSecond() {
    return frameRate;
  }

  //Tick which sets current to the current instance of time
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

  private void resetFrames() {
      frames = 0;
  }

  public void reset() {
      prev = Instant.now();
      this.resetFrames();
  }
}
