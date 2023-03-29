package org.Snake;

import processing.core.PImage;

/**
 * Sprite class which instantiates basic attributes for on screen components
 *
 * @author
 * @version
 */
public class Sprite implements Drawable{
  private float xPos;
  private float yPos;
  private int size;
  private PImage picture;
  private static Window window;
  public static PImage tailLeft;
    public static PImage tailRight;
    public static PImage tailUp;
    public static PImage tailDown;
    public static PImage bodyLeftRight;
    public static PImage bodyUpDown;
    public static PImage cornerLeftUp;
    public static PImage cornerLeftDown;
    public static PImage cornerRightUp;
    public static PImage cornerRightDown;
    public static PImage headLeft;
    public static PImage headRight;
    public static PImage headUp;
    public static PImage headDown;
    public static PImage food;
    public static PImage wall;


  /**
   * Sprite constructor which sets the position and size of the Sprite
   * @param xPos the x position of the Sprite
   * @param yPos the y position of the Sprite
   * @param size the size of the Sprite
   * @param picture the picture of the Sprite
   */
  public Sprite(int xPos, int yPos, int size, String picture) {
    this.xPos = xPos;
    this.yPos = yPos;
    this.size = size;
    try{
      this.picture = window.loadImage(picture);
    }
    catch (Exception e){
      //System.out.println("Image not found");
    }
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
    tailLeft = window.loadImage("src/main/images/tailLeft.png");
    tailRight = window.loadImage("src/main/images/tailRight.png");
    tailUp = window.loadImage("src/main/images/tailUp.png");
    tailDown = window.loadImage("src/main/images/tailDown.png");
    bodyLeftRight = window.loadImage("src/main/images/bodyEW.png");
    bodyUpDown = window.loadImage("src/main/images/bodyNS.png");
    cornerLeftUp = window.loadImage("src/main/images/cornerNE.png");
    cornerLeftDown = window.loadImage("src/main/images/cornerSE.png");
    cornerRightUp = window.loadImage("src/main/images/cornerNW.png");
    cornerRightDown = window.loadImage("src/main/images/cornerSW.png");
    headLeft = window.loadImage("src/main/images/snakeLeft.png");
    headRight = window.loadImage("src/main/images/snakeRight.png");
    headUp = window.loadImage("src/main/images/snakeUp.png");
    headDown = window.loadImage("src/main/images/snakeDown.png");
    food = window.loadImage("src/main/images/apple.png");
    wall = window.loadImage("src/main/images/wall.png");
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
//    window.stroke(0,0,0);
//    window.pushStyle();
//    window.fill(0, 204, 0);
//    window.rect(this.xPos+window.getOffset(), this.yPos, size, size);
//    window.popStyle();
  }
}
