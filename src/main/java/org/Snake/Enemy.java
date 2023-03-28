package org.Snake;

import java.util.Random;

/**
 * Enemy class which is meant to be the enemy of the snake
 * It is still in production, hence the lack of functionality
 */
public class Enemy extends Sprite {

  private int speed;

  private int directionX = 0;
  private int directionY = 0;

  private int rotation;

  /**
   * Enemy constructor which sets the position and size of the enemy
   * @param xPos
   * @param yPos
   * @param size
   */
  public Enemy(int xPos, int yPos, int size, String picture) {
    super(xPos, yPos, size, picture);
    super.setPicture(getWindow().loadImage("src/main/images/enemy.png"));
  }

  /**
   * Getter for the speed of the enemy
   * @return int
   */
  public void move() {

    int choices[] = {37, 65, 39, 68,38, 87,40, 83};
    Random random = new Random();
    int randomIndex = random.nextInt(choices.length);
    int direction = choices[randomIndex];

    switch (direction) {
      case 37, 65:
        //check that the snake is not going right
        if (this.directionX == 1) {
          break;
        }

        // go left
        setDirectionX(-1);
        setDirectionY(0);


        break;
      case 39, 68:
        if (this.directionX == -1) {
          break;
        }
        // handle right
        setDirectionX(1);
        setDirectionY(0);
        break;

      case 38, 87:

        if (this.directionY == 1) {
          break;
        }

        // handle up
        setDirectionY(-1);
        setDirectionX(0);
        break;

      case 40, 83:
        if (this.directionY == -1) {
          break;
        }
        // handle down
        setDirectionY(1);
        setDirectionX(0);


        break;

    }

  }

  /**
   * Setter for the direction x of the enemy
   */
  public void setDirectionX(int i) {
    // set direction x to i
    directionX = i;
  }

  /**
   * Setter for the direction y of the enemy
   */
  public void setDirectionY(int i) {
    // set direction x to i
    directionY = i;
  }

  /**
   * Method which is meant to draw the enemy
   */
  public void draw() {
//    super.getWindow().stroke(0, 0, 0);
    //super.getWindow().pushStyle();
//    super.getWindow().fill(0, 204, 0);
    super.getWindow().image(super.getPicture(),
            super.getxPos() + super.getWindow().getOffset(),
            super.getyPos(),
            super.getSize(),
            super.getSize());
//    super.getWindow().popStyle();
  }
}
