package org.example;


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


  public ArrayList<SnakeBody> getBody() {
    return body;
  }

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
    for (int i = 0; i < INITIALSIZE; i++) {
      if (i == 0) {
        body.add(new SnakeBody(this.getxPos()+i*10, this.getyPos()-(INITIALSIZE)*this.getSize(), this.getSize(), null));
      } else {
        body.add(new SnakeBody(this.getxPos(), this.getyPos()-((INITIALSIZE-i)*this.getSize()), this.getSize(), null));
      }
    }
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

  public void updatePos(int tileWidth) {
    //calculate the next position of the player

  }

  public void move(int lastKeyPressed) {
    int lastDY = -directionY;
    int lastDX = directionX;
    switch (lastKeyPressed) {
      case 37, 65:
        //check that the snake is not going right
        if (this.directionX == 1) {
          break;
        }

        // go left
        setDirectionX(-1);
        setDirectionY(0);
        //up to left == 1
        //down to left == -1
        if (lastDY == 1) {
          body.get(body.size()-1).setCorner(1);
//          System.out.println("up to left");
        } else if (lastDY == -1) {
          body.get(body.size()-1).setCorner(-1);
        }
        super.setPicture(super.headLeft);
//        body.get(0).setPicture(getWindow().loadImage("src/main/images/snakeBodyLeftRight.png"));
        break;
      case 39, 68:
        if (this.directionX == -1) {
          break;
        }
        // handle right
        setDirectionX(1);
        setDirectionY(0);
        //up to right == -2
        //down to right == 2
        if(lastDY == 1) {
          body.get(body.size()-1).setCorner(-2);
        } else if (lastDY == -1) {
          body.get(body.size()-1).setCorner(2);
        }
        super.setPicture(super.headRight);
//        body.get(0).setPicture(getWindow().loadImage("src/main/images/snakeBodyLeftRight.png"));
        break;

      case 38, 87:

        if (this.directionY == 1) {
          break;
        }

        // handle up
        setDirectionY(-1);
        setDirectionX(0);
        //left to up == 2
        //right to up == -1
        if (lastDX == 1) {
          body.get(body.size()-1).setCorner(-1);
        } else if (lastDX == -1) {
          body.get(body.size()-1).setCorner(2);
        }
        super.setPicture(super.headUp);
//        body.get(0).setPicture(getWindow().loadImage("src/main/images/snakeBodyUpDown.png"));
        break;

      case 40, 83:
        if (this.directionY == -1) {
          break;
        }
        // handle down
        setDirectionY(1);
        setDirectionX(0);
        //left to down == -2
        //right to down == 1
        if (lastDX == 1) {
          body.get(body.size()-1).setCorner(1);
        } else if (lastDX == -1) {
          body.get(body.size()-1).setCorner(-2);
        }
        super.setPicture(super.headDown);
//        body.get(0).setPicture(getWindow().loadImage("src/main/images/snakeBodyUpDown.png"));
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


  public int getDirectionX() {
    return directionX;
  }

  public int getDirectionY() {
    return directionY;
  }

  public void reset(int startX, int startY) {
    setDirectionX(0);
    setDirectionY(0);
    setxPos(startX * this.getSize());
    setyPos(startY * this.getSize());
    body.clear();
    for (int i = 0; i < INITIALSIZE; i++) {
      if (i == 0) {
        body.add(new SnakeBody(this.getxPos()+i*10, this.getyPos()-(INITIALSIZE)*this.getSize(), this.getSize(), null));
      } else {
        body.add(new SnakeBody(this.getxPos(), this.getyPos()-((INITIALSIZE-i)*this.getSize()), this.getSize(), null));
      }
    }
//    body.add(body1);
//    body1.setxPos(3 * this.getSize());
//    body1.setyPos(4 * this.getSize());
//    body1.setPicture(getWindow().loadImage("src/main/images/snakeBodyUpDown.png"));
//
//    body.add(body2);
//    body2.setxPos(3 * this.getSize());
//    body2.setyPos(3 * this.getSize());
//    body2.setPicture(getWindow().loadImage("src/main/images/snakeBodyUpDown.png"));
//
//    body.add(tail);
//    tail.setxPos(3 * this.getSize());
//    tail.setyPos(2 * this.getSize());
//    tail.setPicture(getWindow().loadImage("src/main/images/snakeTailUp.png"));
//

    super.setPicture(super.headDown);
  }

//  public void animateBody(float framesPerClock, int i) {
//    float dx = 0;
//    float dy = 0;
//    if (i+1 == body.size()) {
//      dx = this.getxPos() - body.get(i).getxPos();
//      dy = this.getyPos() - body.get(i).getyPos();
//
//    }else {
//      dx = body.get(i+1).getxPos() - body.get(i).getxPos();
//      dy = body.get(i+1).getyPos() - body.get(i).getyPos();
//    }
//    if(Math.abs(dx) > Math.abs(dy)) {
//      dy = 0;
//    } else {
//      dx = 0;
//    }
//    if (dx > 1) dx = 1;
//    if (dx < -1) dx = -1;
//    if (dy > 1) dy = 1;
//    else if (dy < -1) dy = -1;
//
//    if (this.directionX == 0 && this.directionY == 0) {
//      dx = 0;
//      dy = 0;
//    }
//    float moveX = dx * this.getSize()/framesPerClock;
//    float moveY = dy * this.getSize()/framesPerClock;
//    body.get(i).setyPos((float) (body.get(i).getyPos() + 0.2*dy));
//    body.get(i).setxPos((float) (body.get(i).getxPos() + 0.2*dx));
//    body.get(i).setSizeX((int) (body.get(i).getSizeX() *10));
//    body.get(i).setSizeY((int) (body.get(i).getSizeY() *10));
//    body.get(i).draw(dx, dy, false,true);
//
//  }
  public void draw() {
    for (int i = 0; i < body.size(); i++) {
      boolean isTail = false;
      boolean isFront = false;
      int nextX = 0;
      int nextY = 0;
      if(i == 0 ){
        isTail = true;
      }
      if(i == body.size() - 1) {
        nextX = (int)this.getxPos();
        nextY = (int)this.getyPos();
        isFront = true;
      }
      else{
        nextX = (int)body.get(i+1).getxPos();
        nextY = (int)body.get(i+1).getyPos();
      }
      float dx = (nextX - body.get(i).getxPos());
      float dy = (nextY - body.get(i).getyPos());
      if (dx >= 1) dx = 1;
      else if (dx <= -1) dx = -1;
      if (dy >= 1) dy = 1;
      else if (dy <= -1) dy = -1;
      body.get(i).setxPos(body.get(i).getxPos());
      body.get(i).setyPos(body.get(i).getyPos());
      body.get(i).draw(dx, dy, isTail, isFront);
    }
    super.getWindow().image(super.getPicture(),
            super.getxPos() + super.getWindow().getOffset(),
            super.getyPos(),
            super.getSize(),
            super.getSize());
  }
  public void grow() {
    int nextX = 0;
    int nextY = 0;

    if(body.size() == 1) {
      nextX = (int)this.getxPos();
      nextY = (int)this.getyPos();
    }
    else{
      nextX = (int)body.get(1).getxPos();
      nextY = (int)body.get(1).getyPos();
    }
    float dx = (nextX - body.get(0).getxPos());
    float dy = (nextY - body.get(0).getyPos());
    String image = null;
    if (dx >= 0.1) {
      image = "src/main/images/tailLeft.png";
    } else if (dx <= -0.1) {
      image = "src/main/images/tailRight.png";
    } else if (dy >= 0.1) {
      image = "src/main/images/tailUp.png";
    } else if (dy <= -0.1) {
      image = "src/main/images/tailDown.png";
    }

    this.body.add(0,new SnakeBody(body.get(0).getxPos(), body.get(0).getyPos(), this.getSize(), null));
//    this.body.get(0).draw(body.get(0).getxPos(), body.get(0).getyPos(), true);
  }

  public void slither(float nextX, float nextY){
    Sprite firstTile = body.get(0);
  }


  public void moveBody(float prevX, float prevY) {
    if (this.directionX == 0 && this.directionY == 0) {
      return;
    }
    for (int i = 0; i < body.size(); i++) {
      body.get(i).setSizeX(this.getSize());
        body.get(i).setSizeY(this.getSize());
      if (i == body.size()- 1) {
        body.get(i).setxPos(prevX);
        body.get(i).setyPos(prevY);
        body.get(i).setCorner(0);
      } else {
        body.get(i).setxPos(body.get(i + 1).getxPos());
        body.get(i).setyPos(body.get(i + 1).getyPos());
        if(body.get(i+1).isCorner() != 0){
            body.get(i).setCorner(body.get(i+1).isCorner());
            body.get(i+1).setCorner(0);
            } else {
            body.get(i).setCorner(0);
        }
      }
    }
    // move the snake
//    // get the first tile in the snake
//
//    float currX = prevX;
//    float currY = prevY;
//    PImage currPic = body.get(0).getPicture();
//    // move the first tile
//    for (SnakeBody bodyPart : body) {
//      float tempX = bodyPart.getxPos();
//      float tempY = bodyPart.getyPos();
//      PImage tempPic = bodyPart.getPicture();
//      bodyPart.setxPos(currX);
//      bodyPart.setyPos(currY);
//      if (bodyPart != body.get(body.size()-1))bodyPart.setPicture(currPic);
//      currX = tempX;
//      currY = tempY;
//      currPic = tempPic;
//    }
//
//    if (body.get(body.size()-1).getxPos() - body.get(body.size()-2).getxPos() < -10) {
//      body.get(body.size() - 1).setPicture(getWindow().loadImage("src/main/images/snakeTailLeft.png"));
//    }  else if (body.get(body.size()-1).getxPos() - body.get(body.size()-2).getxPos() > 10){
//      body.get(body.size() - 1).setPicture(getWindow().loadImage("src/main/images/snakeTailRight.png"));
//    } else if (body.get(body.size()-1).getyPos() - body.get(body.size()-2).getyPos() < -10){
//      body.get(body.size() - 1).setPicture(getWindow().loadImage("src/main/images/snakeTailUp.png"));
//    } else if (body.get(body.size()-1).getyPos() - body.get(body.size()-2).getyPos() > 10){
//      body.get(body.size() - 1).setPicture(getWindow().loadImage("src/main/images/snakeTailDown.png"));
//    }
  }
  public SnakeBody getBody(int i) {
    return body.get(i);
  }
}
