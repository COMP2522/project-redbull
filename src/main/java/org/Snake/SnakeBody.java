package org.Snake;

import processing.core.PImage;

/**
 * SnakeBody class which represents a body part of the snake.
 *
 * @author The redbull team
 * @version 1.0
 */
public class SnakeBody extends Sprite {
  /**
   * The corner of the SnakeBody.
   */
  private int corner;
  /**
   * The size of the SnakeBody in the x direction.
   */
  private final int sizeX;
  /**
   * The size of the SnakeBody in the y direction.
   */
  private final int sizeY;
  /**
   * The image of the SnakeBody.
   */

  private String image;
  /**
   * The image of the SnakeBody.
   */
  public int tailLeft = 1;

  /**
   * The image of the SnakeBody.
   */
  public int tailRight = -1;
  /**
   * The image of the SnakeBody.
   */
  public int tailUp = 1;
  /**
   * The image of the SnakeBody.
   */
  public int tailDown = -1;

  /**
   * Corner of the SnakeBody.
   */
  public final int cornerRightDown = 1;
  /**
   * Corner of the SnakeBody.
   */
  public final int cornerLeftUp =  2;
  /**
   * Corner of the SnakeBody.
   */
  public final int cornerRightUp = -1;
  /**
   * Corner of the SnakeBody.
   */
  public final int cornerLeftDown = -2;

  /**
   * The constructor of the SnakeBody class.
   *
   * @param xpos float
   * @param ypos float
   * @param size int
   * @param picture String
   */
  public SnakeBody(float xpos, float ypos, int size, String picture) {
    super((int) xpos, (int) ypos, size);
    sizeX = size;
    sizeY = size;
    image = picture;
  }

  /**
   * The method that determines if the SnakeBody is a corner.
   *
   * @return if the SnakeBody is a corner.
   */
  public int isCorner() {
    return corner;
  }

  /**
   * The method that sets the corner of the SnakeBody.
   *
   *  @param corner the corner of the SnakeBody.
   */
  public void setCorner(int corner) {
    this.corner = corner;
  }


  /**
   * The method that draws the SnakeBody.
   *
   */
  public void draw(float directionX, float directionY, boolean isTail) {
    if (isTail) {
      if (directionX >= tailLeft) {
        image = "tailLeft";
      } else if (directionX <= tailRight) {
        image = "tailRight";
      } else if (directionY >= tailUp) {
        image = "tailUp";
      } else if (directionY <= tailDown) {
        image = "tailDown";
      }
    } else if (isCorner() != 0) {
      switch (isCorner()) {
        case cornerRightDown -> image = "cornerRightDown";
        case cornerLeftUp -> image = "cornerLeftUp";
        case cornerRightUp -> image = "cornerRightUp";
        case cornerLeftDown -> image = "cornerLeftDown";
        default -> throw new IllegalStateException("Unexpected value: " + isCorner());
      }

    } else {
      if (directionX >= 0.1 || directionX <= -0.1) {
        image = "bodyLeftRight";
      } else if (directionY <= -0.1 || directionY >= 0.1) {
        image = "bodyUpDown";
      }
    }
    try {

      if (image != null) {
        getWindow().image(getImage(image),
              super.getxPos() + getWindow().getOffset(),
              super.getyPos(),
              this.getSizeX(),
              this.getSizeY());
      }
    } catch (Exception e) {
      //System.out.println("image is null");
    }
  }

  /**
   * The method that sets the X size of the SnakeBody.
   *
   * @param sizeX the x size of the SnakeBody.
   */
  public void setSizeX(int sizeX) {
    super.setSize(sizeX);
  }

  /**
   * The method that sets the Y size of the SnakeBody.
   *
   * @param sizeY the y size of the SnakeBody
   */
  public void setSizeY(int sizeY) {
    super.setSize(sizeY);
  }

  /**
   * The method that gets the X size of the SnakeBody.
   *
   * @return the x size of the SnakeBody
   */
  public int getSizeX() {
    return sizeX;
  }

  /**
   * The method that gets the Y size of the SnakeBody.
   *
   * @return the y size of the SnakeBody
   */
  public int getSizeY() {
    return sizeY;
  }

  /**
   * The method that gets the image of the SnakeBody.
   *
   * @return the image of the SnakeBody
   */
  public PImage getPicture() {
    return super.getPicture();
  }

  /**
   * The method that sets the image of the SnakeBody.
   *
   * @param picture the picture to set.
   */
  @Override
  public void setPicture(PImage picture) {
    super.setPicture(picture);
  }
}
