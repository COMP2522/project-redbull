package org.example;


import java.awt.*;
import java.util.ArrayList;


//import processing.event.KeyEvent;
//
//import java.awt.*;

/**
 * The Snake class. For snake things :)
 * Using the linkedlist library, makes the snake a singleton linked list
 */
public class Snake extends Sprite {

  private int xSpeed;

  private int Yspeed;

  private int direction;

  private ArrayList<Sprite> body;
  private static Snake instance;

  private Snake(int xPos, int yPos, int size, Image picture, Window window) {
    super(xPos, yPos, size, picture, window);
    body = new ArrayList<>();
  }

  // This makes the snake a singleton, but we could probably take this out with the way things are going
  public static Snake getInstance(int xPos, int yPos, int size, Image picture, Window window) {
    if (instance == null) {
      instance = new Snake(xPos, yPos, size, picture, window);
    }
    return instance;
  }

  public int getxSpeed() {
    return xSpeed;
  }

  public void setxSpeed(int xSpeed) {
    this.xSpeed = xSpeed;
  }

  public int getYspeed() {
    return Yspeed;
  }

  public void setYspeed(int yspeed) {
    Yspeed = yspeed;
  }

  public void move(int xSpeed, int ySpeed) {
  }

  public int getDirection() {
    return direction;
  }

  public void setDirection(int direction) {
    this.direction = direction;
  }
//
//  @Override
//  public void keyPressed(KeyEvent event) {
//    int keyCode = event.getKeyCode();
//    switch( keyCode ) {
//      case LEFT:
//        // handle left
//        Snake.setDirection(player.getDirection().rotate(-Window.PI / 16));
//        break;
//      case RIGHT:
//        // handle right
//        Snake.setDirection(Snake.getDirection().rotate(Window.PI / 16));
//        break;
//
//      case UP:
//        // handle up
//        Snake.setDirection(Snake.getDirection().rotate(Window.PI / 16));
//        break;
//
//      case DOWN:
//        // handle down
//        Snake.setDirection(Snake.getDirection().rotate(Window.PI / 16));
//        break;
//
//    }


}