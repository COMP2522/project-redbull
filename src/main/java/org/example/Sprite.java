package org.example;

import java.awt.*;

//Sprite class which instantiates basic attributes for on screen components
public class Sprite {
  private float xPos;
  private float yPos;
  private int size;
  private Image picture;
  private static Window window;

  //Public sprite constructor taking in x, y , size, and picture
  public Sprite(int xPos, int yPos, int size, Image picture) {
    this.xPos = xPos;
    this.yPos = yPos;
    this.size = size;
    this.picture = picture;
  }

  public static void setWindow(Window window) {
    Sprite.window = window;
  }

  public float getxPos() {
    return xPos;
  }

  public float getyPos() {
    return yPos;
  }

  public int getSize() {
    return size;
  }

  public Image getPicture() {
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

  public void setPicture(Image picture) {
    this.picture = picture;
  }

  //Draw method to instantiate the window with elements.
  public void draw(){
    window.stroke(0,0,0);
    window.pushStyle();
    window.fill(0, 204, 0);
    window.rect(this.xPos+window.getOffset(), this.yPos, size, size);
    window.popStyle();
  }
}
