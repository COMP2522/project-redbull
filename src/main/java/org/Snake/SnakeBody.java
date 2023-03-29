package org.Snake;

import processing.core.PImage;

/**
 * SnakeBody class which represents a body part of the snake
 *
 * @author
 * @version
 */
public class SnakeBody extends Sprite {

  private int rotation;
  private int corner;
  private int sizeX;
  private int sizeY;

  /**
   * SnakeBody constructor which sets the position and size of the SnakeBody
   * @param xPos the x position of the SnakeBody
   * @param yPos the y position of the SnakeBody
   * @param size the size of the SnakeBody
   * @param picture the picture of the SnakeBody
   */
  public SnakeBody(float xPos, float yPos, int size, String picture) {
    super((int)xPos, (int)yPos, size, null);
    sizeX = size;
    sizeY = size;
  }

  /**
   * The method that determines if the SnakeBody is a corner
   * @return
   */
  public int isCorner() {
    return corner;
  }

  /**
   * The method that sets the corner of the SnakeBody
   * @param corner
   */
    public void setCorner(int corner) {
        this.corner = corner;
    }

  //left to down == -2
  //right to down == 1
  //left to up == 2
  //right to up == -1

  //up to right == -2
  //down to right == 2
  //up to left == 1
  //down to left == -1
  public void draw(float directionX, float directionY, boolean isTail, boolean isFront){
    PImage image = super.getPicture();
//    System.out.println("directionX: " + directionX + " directionY: " + directionY);
//    System.out.println("isTail: " + isTail);
//    System.out.println("isCorner: " + this.isCorner());
    if (isTail) {
      if (directionX >= 1) {
        image = super.tailLeft;
      } else if (directionX <= -1) {
        image = super.tailRight;
      } else if (directionY >= 1) {
        image = super.tailUp;
      } else if (directionY <= -1) {
        image = super.tailDown;
      }
    } else if (isCorner() != 0) {
      switch (isCorner()) {
        case 1:
          image = super.cornerRightDown;
          break;
        case 2:
          image = super.cornerLeftUp;
          break;
        case -1:
          image = super.cornerRightUp;
          break;
        case -2:
          image = super.cornerLeftDown;
          break;
      }
    } else {
//      System.out.println("directionX: " + directionX + " directionY: " + directionY);
      if (directionX >= 0.1 || directionX <= -0.1) {
        image = super.bodyLeftRight;
      }else if (directionY <= -0.1 || directionY >= 0.1) {
        image = super.bodyUpDown;
      }
    }
    try {

    if (image != null) {
      super.getWindow().image(image,
              super.getxPos() + super.getWindow().getOFFSET(),
              super.getyPos(),
              this.getSizeX(),
              this.getSizeY());
    }
    } catch (Exception e) {
      //System.out.println("image is null");
    }
    //super.getWindow().popStyle();
//    System.out.println("SnakeBody.draw() called. (xPos, yPos) = (" + super.getxPos() + ", " + super.getyPos() + ")");
  }

  /**
   * The method that sets the X size of the SnakeBody
   * @param sizeX the x size of the SnakeBody
   */
  public void setSizeX(int sizeX) {
    super.setSize(sizeX);
  }

  /**
   * The method that sets the Y size of the SnakeBody
   * @param sizeY the y size of the SnakeBody
   */
  public void setSizeY(int sizeY) {
    super.setSize(sizeY);
  }

  /**
   * The method that gets the X size of the SnakeBody
   * @return
   */
    public int getSizeX() {
        return sizeX;
    }

    /**
     * The method that gets the Y size of the SnakeBody
     * @return
     */
    public int getSizeY() {
        return sizeY;
    }

  public PImage getPicture(){
    return super.getPicture();
  }

  @Override
  public void setPicture(PImage picture) {
    super.setPicture(picture);
  }
}
