package org.Snake.Enemies;

import org.Snake.Enemy;

import java.util.Random;

public class Beetle extends Enemy {

  private Random random;

  private String image;

  /**
   * Enemy constructor which sets the position and size of the enemy
   *
   * @param xPos
   * @param yPos
   * @param size
   * @param picture
   */
  public Beetle(int xPos, int yPos, int size, String picture) {

    super(xPos, yPos, size, picture);
    super.setPicture(getWindow().loadImage(picture));
  }
}
