package org.Snake;

import java.util.Random;

/**
 * Enemy class which is meant to be the enemy of the snake.
 *
 * @author the redbull team
 * @version 1.0
 */
public class Enemy extends Sprite {

  /**
   * The speed of the enemy.
   */
  private int speed;

  private final String image;

  /**
   * Enemy constructor which sets the position and size of the enemy.
   *
   * @param xpos the x position of the enemy
   * @param ypos the y position of the enemy
   * @param size the size of the enemy
   */
  public Enemy(int xpos, int ypos, int size, String picture) {
    super(xpos, ypos, size);
    this.image = picture;
  }

  /**
   * Move method for the enemy.
   */
  public void move() {
  }

  /**
   * Method which is meant to draw the enemy
   */
  public void draw() {
        getWindow().image(getImage(image),
            super.getxPos() + super.getWindow().getOffset(),
            super.getyPos(),
            super.getSize(),
            super.getSize());
  }
}
