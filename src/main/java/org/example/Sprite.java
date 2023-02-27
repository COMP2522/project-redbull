package org.example;

import java.awt.*;

public class Sprite {
  private int xPos;
  private int yPos;
  private int size;
  private Image picture;
  private Window window;

  public Sprite(int xPos, int yPos, int size, Image picture, Window window) {
    this.xPos = xPos;
    this.yPos = yPos;
    this.size = size;
    this.picture = picture;
    this.window = window;
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
    this.xPos = xPos+window.offset;
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
    window.fill(50, 50, 50);
    window.ellipse(this.xPos, this.yPos, size, size);
    window.popStyle();
  }
}
