package org.Snake;

import processing.core.PImage;

/**
 * SnakeBody class which represents a body part of the snake
 *
 * @author
 * @version 1.0
 */
public class SnakeBody extends Sprite {
  private int corner;
  private final int sizeX;
  private final int sizeY;

  private String image;

  public int tailLeft = 1;
  public int tailRight = -1;
  public int tailUp = 1;
  public int tailDown = -1;

  public final int cornerRightDown = 1;
  public final int cornerLeftUp =  2;
  public final int cornerRightUp = -1;
  public final int cornerLeftDown = -2;

  /**
   *
   * @param xPos
   * @param yPos
   * @param size
   * @param picture
   */
  public SnakeBody(float xPos, float yPos, int size, String picture) {
    super((int)xPos, (int)yPos, size);
    sizeX = size;
    sizeY = size;
    image = picture;
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



  public void draw(float directionX, float directionY, boolean isTail){
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
      }

    } else {
//      System.out.println("directionX: " + directionX + " directionY: " + directionY);
      if (directionX >= 0.1 || directionX <= -0.1) {
        image = "bodyLeftRight";
      }else if (directionY <= -0.1 || directionY >= 0.1) {
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
