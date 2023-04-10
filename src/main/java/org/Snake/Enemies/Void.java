package org.Snake.Enemies;

import org.Snake.Enemy;

public class Void extends Enemy {

  private String image;

  /**
   * Constructor which sets the position and size of the enemy
   *
   * @param xPos
   * @param yPos
   * @param size
   * @param picture
   */
  public Void(int xPos, int yPos, int size, String picture) {

    super(xPos, yPos, size, picture);
  }


}
