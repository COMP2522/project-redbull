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

  /**
   * Move method with that moves the beetle randomly, uses position not direction
   * @return int
   */
  public void move() {
    Random random = new Random();
    //randomly choose a direction
    int randomX = random.nextInt(2) == 0 ? -1 : 1;
    int randomY = random.nextInt(2) == 0 ? -1 : 1;

    // calculate new positions
    int newX = (int) (super.getxPos() + randomX * super.getSize());
    int newY = (int) (super.getyPos() + randomY * super.getSize());

    // check if the move is valid (within the window bounds)
    if (newX >= 0 && newX < getWindow().getWidth() - super.getSize() &&
        newY >= 0 && newY < getWindow().getHeight() - super.getSize()) {
      // update position
      super.setxPos(newX);
      super.setyPos(newY);
    }
  }


}
