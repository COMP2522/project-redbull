package org.example;


import java.awt.*;
import java.util.ArrayList;

import static processing.core.PApplet.radians;


/**
 * The Snake class. For snake things :)
 * Using the linkedlist library, makes the snake a singleton linked list
 */
public class Snake extends Sprite {

  private int speed;

  private int directionX;
  private int directionY;

  private int rotation;

  // 0 - right
  // 1 - down
  // 2 - left
  // 3 - up

  private ArrayList<Sprite> body;
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
        if(this.directionX == 1){break;}

        // go left
        setDirectionX(-1);
        setDirectionY(0);
        super.setPicture(getWindow().loadImage("src/main/images/snakeLeft.png"));
        break;
      case 39, 68:
        if(this.directionX == -1){break;}
        // handle right
        setDirectionX(1);
        setDirectionY(0);
        super.setPicture(getWindow().loadImage("src/main/images/snakeRight.png"));
        break;

      case 38, 87:
        if(this.directionY == 1){break;}
        // handle up
        setDirectionY(-1);
        setDirectionX(0);
        super.setPicture(getWindow().loadImage("src/main/images/snakeUp.png"));
        break;

      case 40, 83:
        if(this.directionY == -1){break;}
        // handle down
        setDirectionY(1);
        setDirectionX(0);
        super.setPicture(getWindow().loadImage("src/main/images/snakeDown.png"));
        break;

    }

  }

  public void setRotation(int i) {
    this.rotation = i;
  }
  public int getRotation(){
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

  public void reset() {
    body.clear();
    setDirectionX(0);
    setDirectionY(0);
    setxPos(0);
    setyPos(1*this.getSize());

  }

  public void draw(){
    super.getWindow().stroke(0,0,0);
    super.getWindow().pushStyle();
    super.getWindow().fill(0, 204, 0);
//    super.getWindow().rect(
//            super.getxPos()+super.getWindow().getOffset(),
//            super.getyPos(),
//            super.getSize(),
//            super.getSize());
    super.getWindow().image(super.getPicture(),
            super.getxPos()+super.getWindow().getOffset(),
            super.getyPos(),
            super.getSize(),
            super.getSize());
    super.getWindow().popStyle();
  }

//  public void grow() {
//    // add a new tile to the end of the snake
//    // get the last tile in the snake
//    Sprite lastTile = body.get(body.size() - 1);
//    // create a new tile at the same position as the last tile
//    Sprite newTile = new Sprite(lastTile.getxPos(), lastTile.getyPos(), lastTile.getSize(), lastTile.getPicture());
//    // add the new tile to the snake
//    body.add(newTile);
//  }


  }
