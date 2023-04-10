package org.Snake.Enemies;

import java.util.Random;
import org.Snake.Enemy;
import org.Snake.Wall;


/**
 * Beetle class which is meant to be the enemy of the snake.
 *
 * @author the redbull team
 * @version 1.0
 */
public class Beetle extends Enemy {


  public void setInbounds(boolean inbounds) {
    this.inbounds = inbounds;
  }

  private boolean inbounds = true;

  /**
   * Beetle constructor which sets the position and size of the enemy.
   *
   * @param xPos the x position of the beetle
   * @param yPos the y position of the beetle
   * @param size the size of the beetle
   * @param picture the picture of the beetle
   */
  public Beetle(int xPos, int yPos, int size, String picture) {

    super(xPos, yPos, size, picture);
    super.setPicture(getWindow().loadImage(picture));
  }

  public boolean isWithinBounds(int x, int y) {
    return x >= 0 && x < getWindow().getWidth() - super.getSize() &&
        y >= 0 && y < getWindow().getHeight() - super.getSize();
  }


  /**
   * Move method with that moves the beetle randomly, uses position not direction.
   */
  public void move() {
    Random random = new Random();

    // Choose either X or Y axis for movement
    boolean moveX = random.nextBoolean();

    //randomly choose a direction
    int randomX = random.nextInt(2) == 0 ? -1 : 1;
    int randomY = random.nextInt(2) == 0 ? -1 : 1;

    // calculate new positions
    int newX = (int) (super.getxPos() + (moveX ? randomX * super.getSize() : 0));
    int newY = (int) (super.getyPos() + (!moveX ? randomY * super.getSize() : 0));

    // check if the move is valid (within the window bounds)
    if (isWithinBounds(newX, newY)) {
      // update position
      super.setxPos(newX);
      super.setyPos(newY);
    } else {
      this.inbounds = false;
    }
  }


  public boolean isInbounds() {
    return inbounds;
  }

  /**
   * Method to check if beetles are in bounds
   */
  public boolean isWithinBounds(Beetle beetle, Wall[][] walls) {


    return true;
  }
}
