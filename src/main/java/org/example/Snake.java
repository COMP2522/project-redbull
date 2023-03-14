package org.example;


import java.awt.*;
import java.util.ArrayList;


/**
 * The Snake class. For snake things :)
 * Using the linkedlist library, makes the snake a singleton linked list
 */
public class Snake extends Sprite {

  private int speed;

  private int direction;

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
                setDirection(2);
                break;
            case 39:
                // handle right
                setDirection(0);

                break;

            case 38:
                // handle up
                setDirection(3);
                break;

            case 40:
                // handle down
                setDirection(1);
                break;

        }
    // change the position based on the direction of the snake
    // 0 - right
    // 1 - down
    // 2 - left
    // 3 - up

    if (direction == 0) {
      setxPos(getxPos() + speed);
    } else if (direction == 1) {
      setyPos(getyPos() + speed);
    } else if (direction == 2) {
      setxPos(getxPos() - speed);
    } else if (direction == 3) {
      setyPos(getyPos() - speed);
    }
  }

//Returns current direction
  public int getDirection() {
    return direction;
  }

  //Sets direction
  public void setDirection(int direction) {
    this.direction = direction;
  }

  }
