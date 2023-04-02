package org.Snake;

import processing.core.PImage;

import java.util.HashMap;
import java.util.Map;

//Sprite class which instantiates basic attributes for on screen components
public class Sprite implements Drawable{
  private float xPos;
  private float yPos;
  private int size;
  private PImage picture;
  static Map<String, PImage> images = new HashMap<>();
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
    images.put("food",window.loadImage("src/main/images/apple.png"));
    images.put("wall",window.loadImage("src/main/images/wall.png"));
  }
    public static PImage getImage(String name){
        return images.get(name);
    }

  /**
   * The getter of the window
   * @return
   */
  public static Window getWindow(){return Sprite.window;}

  public float getxPos() {
    return xPos;
  }

  public float getyPos() {
    return yPos;
  }

  public int getSize() {
    return size;
  }

  public PImage getPicture() {
    return picture;
  }

  public void setxPos(float xPos) {
    this.xPos = xPos;
  }

  public void setyPos(float yPos) {
    this.yPos = yPos;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public void setPicture(PImage picture) {
    this.picture = picture;
  }

  //Draw method to instantiate the window with elements.
  public void draw(){
  }
}
