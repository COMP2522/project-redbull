package org.example;


import java.util.ArrayList;


/**
 * The Snake class. For snake things :)
 * Using the linkedlist library, makes the snake a singleton linked list
 */
public class Snake extends Sprite {

  private int speed;

  private int directionX;
  private int directionY;

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
        break;
      case 39, 68:
        if (this.directionX == -1) {
          break;
        }
        // handle right
        setDirectionX(1);
        setDirectionY(0);
        super.setPicture(getWindow().loadImage("src/main/images/snakeRight.png"));
        break;

      case 38, 87:
        if (this.directionY == 1) {
          break;
        }
        // handle up
        setDirectionY(-1);
        setDirectionX(0);
        super.setPicture(getWindow().loadImage("src/main/images/snakeUp.png"));
        break;

      case 40, 83:
        if (this.directionY == -1) {
          break;
        }
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
    grow(body1);
    body1.setxPos(5 * this.getSize());
    body1.setyPos(5 * this.getSize());

    grow(body2);
    body2.setxPos(5 * this.getSize());
    body2.setyPos(4 * this.getSize());

    grow(tail);
    tail.setxPos(5 * this.getSize());
    tail.setyPos(3 * this.getSize());

    setDirectionX(0);
    setDirectionY(0);
    setxPos(5 * this.getSize());
    setyPos(6 * this.getSize());
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
    // move the first tile
    for (SnakeBody bodyPart : body) {
      float tempX = bodyPart.getxPos();
      float tempY = bodyPart.getyPos();
      bodyPart.setxPos(currX);
      bodyPart.setyPos(currY);
      currX = tempX;
      currY = tempY;
    }
  }
}
