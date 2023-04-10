package org.Snake;

import processing.core.PImage;

import java.util.HashMap;
import java.util.Map;

/**
 * A class representing a drawable sprite with basic attributes for on-screen components.
 * The class includes a constructor taking x, y, and size parameters for the sprite.
 * It also provides methods for loading and retrieving images for the sprite, and for getting and setting
 * the sprite's x and y positions, size, and image.
 * The class includes static methods for loading and retrieving images, and for setting and getting the window
 * on which the sprite is drawn.
 *
 * @author
 * @version 1.0
 */
public class Sprite implements Drawable{
  /**
   * The x position of the sprite
   */
  private float xPos;
  /**
   * The y position of the sprite
   */
  private float yPos;
  /**
   * The size of the sprite
   */
  private int size;
  /**
   * The image of the sprite
   */
  private PImage picture;
  /**
   * The map of images
   */
  static Map<String, PImage> images = new HashMap<>();
  /**
   * The window on which the sprite is drawn
   */
  private static Window window;
  //Public sprite constructor taking in x, y , size, and picture
  public Sprite(int xPos, int yPos, int size) {
    this.xPos = xPos;
    this.yPos = yPos;
    this.size = size;
  }

  /**
   * The setter of the window
   * @param window
   */
  public static void setWindow(Window window) {
    Sprite.window = window;
  }

  /**
   * The method that loads all the images
   */
  public static void loadImages(){
    images.put("tailLeft",window.loadImage("src/main/images/tailLeft.png"));
    images.put("tailRight",window.loadImage("src/main/images/tailRight.png"));
    images.put("tailUp",window.loadImage("src/main/images/tailUp.png"));
    images.put("tailDown",window.loadImage("src/main/images/tailDown.png"));
    images.put("bodyLeftRight",window.loadImage("src/main/images/bodyEW.png"));
    images.put("bodyUpDown",window.loadImage("src/main/images/bodyNS.png"));
    images.put("cornerLeftUp",window.loadImage("src/main/images/cornerNE.png"));
    images.put("cornerLeftDown",window.loadImage("src/main/images/cornerSE.png"));
    images.put("cornerRightUp",window.loadImage("src/main/images/cornerNW.png"));
    images.put("cornerRightDown",window.loadImage("src/main/images/cornerSW.png"));
    images.put("headLeft",window.loadImage("src/main/images/snakeLeft.png"));
    images.put("headRight",window.loadImage("src/main/images/snakeRight.png"));
    images.put("headUp",window.loadImage("src/main/images/snakeUp.png"));
    images.put("headDown",window.loadImage("src/main/images/snakeDown.png"));
    images.put("food",window.loadImage("src/main/images/redbull.png"));
    images.put("wall",window.loadImage("src/main/images/wall.png"));

//    enemy images
    images.put("beetle",window.loadImage("src/main/images/beetleDown.png"));
    images.put("void",window.loadImage("src/main/images/void.png"));
  }
    public static PImage getImage(String name){
        return images.get(name);
    }

  /**
   * The getter of the window
   * @return
   */
  public static Window getWindow(){return Sprite.window;}
  /**

   Returns the x position of the sprite.
   @return the x position of the sprite
   */
  public float getxPos() {
    return xPos;
  }
  /**

   Returns the y position of the sprite.
   @return the y position of the sprite
   */
  public float getyPos() {
    return yPos;
  }
  /**

   Returns the size of the sprite.
   @return the size of the sprite
   */
  public int getSize() {
    return size;
  }
  /**

   Returns the picture of the sprite.
   @return the picture of the sprite
   */
  public PImage getPicture() {
    return picture;
  }
  /**

   Sets the x position of the sprite.
   @param xPos the x position to set
   */
  public void setxPos(float xPos) {
    this.xPos = xPos;
  }
  /**

   Sets the y position of the sprite.
   @param yPos the y position to set
   */
  public void setyPos(float yPos) {
    this.yPos = yPos;
  }
  /**

   Sets the size of the sprite.
   @param size the size to set
   */
  public void setSize(int size) {
    this.size = size;
  }
  /**

   Sets the picture of the sprite.
   @param picture the picture to set
   */
  public void setPicture(PImage picture) {
    this.picture = picture;
  }
  /**

   Draws the sprite.
   */
  public void draw() {
  }
}
