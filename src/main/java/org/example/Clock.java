package org.example;
import java.time.Duration;
import java.time.Instant;

import static java.lang.Math.abs;

//Clock class which is meant to track passing time
public class Clock {

    Instant prev;

    Instant current;

    double tickTime;

    //Public clock constructor
    public Clock() {
      prev = Instant.now();
      tickTime = 500;
    }
//Tick which sets current to the current instance of time
  public boolean tick() {

    current = Instant.now();

    Duration timeElapsed = Duration.between(prev, current);
    System.out.println(abs(timeElapsed.toMillis()));

    if (timeElapsed.toMillis() > tickTime) {
      prev = Instant.now();
      return true;
    }
    return false;
  }
}
