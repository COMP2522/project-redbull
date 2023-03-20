package org.example;


import processing.core.PImage;

import java.util.ArrayList;


/**
 * The Snake class. For snake things :)
 * Using the linkedlist library, makes the snake a singleton linked list
 */
public class Snake extends Sprite {

  private int speed;

  private int directionX = 0;
  private int directionY = 0;

  private int rotation;

  private final int  INITIALSIZE = 3;
  private ArrayList <SnakeBody> body;

  // 0 - right
  // 1 - down
  // 2 - left
  // 3 - up

  private static Snake instance;

  //Private static constructor to make class singleTon
  private Snake(int xPos, int yPos, int size, String picture) {
    super(xPos, yPos, size, picture);
    body = new ArrayList<>();
  }

  // Get instance method to instantiate the Snake so only one instance occurs - Singleton
  public static Snake getInstance(int xPos, int yPos, int size, String picture) {
    if (instance == null) {
      instance = new Snake(xPos, yPos, size, picture);
    }
    return instance;
  }

  public static Snake getInstance() throws Kale_Doesnt_know_how_to_Code_Exception {
    if (instance == null) {
      throw new Kale_Doesnt_know_how_to_Code_Exception("Snake has not been instantiated yet");
      // via cam
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
      case 37, 65:
        //check that the snake is not going right
        if (this.directionX == 1) {
          break;
        }

        // go left
        setDirectionX(-1);
        setDirectionY(0);
        super.setPicture(getWindow().loadImage("src/main/images/snakeLeft.png"));
        body.get(0).setPicture(getWindow().loadImage("src/main/images/snakeBodyLeftRight.png"));
        break;
      case 39, 68:
        if (this.directionX == -1) {
          break;
        }
        // handle right
        setDirectionX(1);
        setDirectionY(0);
        super.setPicture(getWindow().loadImage("src/main/images/snakeRight.png"));
        body.get(0).setPicture(getWindow().loadImage("src/main/images/snakeBodyLeftRight.png"));
        break;

      case 38, 87:

        if (this.directionY == 1) {
          break;
        }

        // handle up
        setDirectionY(-1);
        setDirectionX(0);
        super.setPicture(getWindow().loadImage("src/main/images/snakeUp.png"));
        body.get(0).setPicture(getWindow().loadImage("src/main/images/snakeBodyUpDown.png"));
        break;

      case 40, 83:
        if (this.directionY == -1) {
          break;
        }
        // handle down
        setDirectionY(1);
        setDirectionX(0);
        super.setPicture(getWindow().loadImage("src/main/images/snakeDown.png"));
        body.get(0).setPicture(getWindow().loadImage("src/main/images/snakeBodyUpDown.png"));
        break;

    }

  }

  public void setRotation(int i) {
    this.rotation = i;
  }

  public int getRotation() {
    return this.rotation;
  }

  public void setDirectionX(int i) {
    // set direction x to i
    directionX = i;
  }

  public void setDirectionY(int i) {
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

  public void reset(SnakeBody body1, SnakeBody body2, SnakeBody tail) {
    body.clear();
    body.add(body1);
    body1.setxPos(5 * this.getSize());
    body1.setyPos(4 * this.getSize());
    body1.setPicture(getWindow().loadImage("src/main/images/snakeBodyUpDown.png"));

    body.add(body2);
    body2.setxPos(5 * this.getSize());
    body2.setyPos(3 * this.getSize());
    body2.setPicture(getWindow().loadImage("src/main/images/snakeBodyUpDown.png"));

    body.add(tail);
    tail.setxPos(5 * this.getSize());
    tail.setyPos(2 * this.getSize());
    tail.setPicture(getWindow().loadImage("src/main/images/snakeTailUp.png"));

    setDirectionX(0);
    setDirectionY(0);
    setxPos(5 * this.getSize());
    setyPos(5 * this.getSize());
    super.setPicture(getWindow().loadImage("src/main/images/snakeDown.png"));
  }

  public void draw() {
    super.getWindow().stroke(0, 0, 0);
    super.getWindow().pushStyle();
    super.getWindow().fill(0, 204, 0);
    super.getWindow().image(super.getPicture(),
        super.getxPos() + super.getWindow().getOffset(),
        super.getyPos(),
        super.getSize(),
        super.getSize());
    super.getWindow().popStyle();
  }

  public void grow(SnakeBody body) {
    this.body.add(body);
  }

  public void slither(float nextX, float nextY){
    Sprite firstTile = body.get(0);
  }


  public void moveBody(float prevX, float prevY) {
    // move the snake
    // get the first tile in the snake

    float currX = prevX;
    float currY = prevY;
    PImage currPic = body.get(0).getPicture();
    // move the first tile
    for (SnakeBody bodyPart : body) {
      float tempX = bodyPart.getxPos();
      float tempY = bodyPart.getyPos();
      PImage tempPic = bodyPart.getPicture();
      bodyPart.setxPos(currX);
      bodyPart.setyPos(currY);
      if (bodyPart != body.get(body.size()-1))bodyPart.setPicture(currPic);
      currX = tempX;
      currY = tempY;
      currPic = tempPic;
    }
    System.out.println("X diff: " + (body.get(body.size()-1).getxPos() - body.get(body.size()-2).getxPos())
      + "   Y diff: " + (body.get(body.size()-1).getyPos() - body.get(body.size()-2).getyPos()));
    if (body.get(body.size()-1).getxPos() - body.get(body.size()-2).getxPos() < -10) {
      body.get(body.size() - 1).setPicture(getWindow().loadImage("src/main/images/snakeTailLeft.png"));
    }  else if (body.get(body.size()-1).getxPos() - body.get(body.size()-2).getxPos() > 10){
      body.get(body.size() - 1).setPicture(getWindow().loadImage("src/main/images/snakeTailRight.png"));
    } else if (body.get(body.size()-1).getyPos() - body.get(body.size()-2).getyPos() < -10){
      body.get(body.size() - 1).setPicture(getWindow().loadImage("src/main/images/snakeTailUp.png"));
    } else if (body.get(body.size()-1).getyPos() - body.get(body.size()-2).getyPos() > 10){
      body.get(body.size() - 1).setPicture(getWindow().loadImage("src/main/images/snakeTailDown.png"));
    }

  }
}
