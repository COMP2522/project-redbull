package org.example;

import java.time.Duration;
import java.time.Instant;

//Clock class which is meant to track passing time
public class Clock {

  private int frames;

  private int lastFrames;

  Instant prev;

    Instant current;

    double tickTime;

    //Public clock constructor
    public Clock() {
      prev = Instant.now();
      tickTime = 50;
    }
//Tick which sets current to the current instance of time
  public boolean tick() {
    frames++;
    current = Instant.now();
    Duration timeElapsed = Duration.between(prev, current);
    //System.out.println(abs(timeElapsed.toMillis()));
    if (timeElapsed.toMillis() > tickTime) {
      prev = Instant.now();
      return true;
    }
    return false;
  }

  public int getFramesPerClock() {
    resetFrames();
    return lastFrames; //return frames
  }

  private void resetFrames() {
      lastFrames = frames;
      frames = 0; //reset frames
  }

  public void reset() {
      prev = Instant.now();
      this.resetFrames();
  }
}
