package org.example;

import java.awt.*;

public class Sprite {
  private int xPos;
  private int yPos;
  private int size;
  private Image picture;
  private static Window window;

  public Sprite(int xPos, int yPos, int size, Image picture) {
    this.xPos = xPos;
    this.yPos = yPos;
    this.size = size;
    this.picture = picture;
  }

  public static void setWindow(Window window) {
    Sprite.window = window;
  }

  public int getxPos() {
    return xPos;
  }

  public int getyPos() {
    return yPos;
  }

  public int getSize() {
    return size;
  }

  public Image getPicture() {
    return picture;
  }


  public void setxPos(int xPos) {
    this.xPos = xPos;
  }

  public void setyPos(int yPos) {
    this.yPos = yPos;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public void setPicture(Image picture) {
    this.picture = picture;
  }

  public void draw(){
    window.stroke(0,0,0);
    window.pushStyle();
    window.fill(0, 204, 0);
    window.rect(this.xPos+window.offset, this.yPos, size, size);
    window.popStyle();
  }
}