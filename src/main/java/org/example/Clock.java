package org.example;
import java.time.Duration;
import java.time.Instant;

import static java.lang.Math.abs;

public class Clock {

    Instant prev;

    Instant current;

    double tickTime;

    public Clock() {
      prev = Instant.now();
      tickTime = 500;
    }

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
