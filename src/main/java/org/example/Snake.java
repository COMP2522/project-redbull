package org.example;


import java.awt.*;
import java.util.ArrayList;


/**
 * The Snake class. For snake things :)
 * Using the linkedlist library, makes the snake a singleton linked list
 */
public class Snake extends Sprite {

  private int speed;

  private int directionX;
  private int directionY;

  // 0 - right
  // 1 - down
  // 2 - left
  // 3 - up

  private ArrayList<Sprite> body;
  private static Snake instance;

  //Private static constructor to make class singleTon
  private Snake(int xPos, int yPos, int size, Image picture) {
    super(xPos, yPos, size, picture);
    body = new ArrayList<>();
  }

  // Get instance method to instantiate the Snake so only one instance occurs - Singleton
  public static Snake getInstance(int xPos, int yPos, int size, Image picture) {
    if (instance == null) {
      instance = new Snake(xPos, yPos, size, picture);
    }
    return instance;
  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public void move(int lastKeyPressed) {
    switch (lastKeyPressed) {
      case 37:
        // go left
        setDirectionX(-1);
        setDirectionY(0);
        break;
      case 39:
        // handle right
        setDirectionX(1);
        setDirectionY(0);

        break;

      case 38:
        // handle up
        setDirectionY(-1);
        setDirectionX(0);
        break;

      case 40:
        // handle down
        setDirectionY(1);
        setDirectionX(0);
        break;

    }

  }

  private void setDirectionX(int i) {
    // set direction x to i
    directionX = i;
  }
  private void setDirectionY(int i) {
    // set direction x to i
    directionY = i;
  }

//Returns current direction
//  public int getDirection() {
//    return direction;
//  }

  public int getDirectionX() {
    return directionX;
  }
  public int getDirectionY() {
    return directionY;
  }


  }
