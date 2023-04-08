package org.Snake;


import java.util.ArrayList;


/**
 * The Snake class. For snake things :)
 * Using the ArrayList library, makes the snake a singleton  ArrayList
 *
 * @author
 * @version 1.0
 */
public class Snake extends Sprite {
  /**
   * The direction the snake is moving in the x direction
   */
  private int directionX = 0;
  /**
   * The direction the snake is moving in the y direction
   */
  private int directionY = 0;

  /**
   * Initial size of the snake
   */
  private final int INITIALIZE = 3;
  /**
   * Image of the snake
   */
  private String image;


  public ArrayList<SnakeBody> getBody() {
    return body;
  }

  private final ArrayList<SnakeBody> body;

  // 0 - right
  // 1 - down
  // 2 - left
  // 3 - up



  //Key Pressed Keycodes for switch statements
  private final int left = 37;
  private final int keyBoardA =65;
  private final int right = 39;
  private final int keyBoardD = 68;
  private final int up = 38;
  private final int  keyBoardW = 87;

  private final int down = 40;
  private final int keyBoardS = 83;
  /**
   * Instance of the snake.
   */
  private static Snake instance;

  //Private static constructor to make class singleTon
  private Snake(int xPos, int yPos, int size, String picture) {
    super(xPos, yPos, size);
    body = new ArrayList<>();
    this.image = picture;
    for (int i = 0; i < INITIALIZE; i++) {
      if (i == 0) {
        body.add(new SnakeBody(this.getxPos() + 0,
                this.getyPos() - (INITIALIZE) * this.getSize(), this.getSize(), null));
      } else {
        body.add(new SnakeBody(this.getxPos(), this.getyPos() - ((INITIALIZE - i)
                * this.getSize()), this.getSize(), null));
      }
    }
  }



  /**
   * Get instance method to instantiate the Snake so only one instance occurs - Singleton
   * @param xPos int
   * @param yPos int
   * @param size int
   * @param picture String
   * @return Snake
   */
  public static Snake getInstance(int xPos, int yPos, int size, String picture) {
    if (instance == null) {
      instance = new Snake(xPos, yPos, size, picture);

    }
    return instance;
  }

  public void move(int lastKeyPressed) {
    int lastDY = -directionY;
    int lastDX = directionX;
    switch (lastKeyPressed) {
      case left, keyBoardA -> {
        //check that the snake is not going right
        if (this.directionX == 1) {
          break;
        }

        // go left
        setDirectionX(-1);
        setDirectionY(0);
        if (lastDY == 1) {
          body.get(body.size() - 1).setCorner(1);
        } else if (lastDY == -1) {
          body.get(body.size() - 1).setCorner(-1);
        }
        image = "headLeft";
      }
      case right, keyBoardD -> {
        if (this.directionX == -1) {
          break;
        }
        // handle right
        setDirectionX(1);
        setDirectionY(0);
        if (lastDY == 1) {
          body.get(body.size() -1).setCorner(-2);
        } else if (lastDY == -1) {
          body.get(body.size() -1).setCorner(2);
        }
        image = "headRight";
      }
      case up, keyBoardW -> {
        if (this.directionY == 1) {
          break;
        }

        // handle up
        setDirectionY(-1);
        setDirectionX(0);
        if (lastDX == 1) {
          body.get(body.size() - 1).setCorner(-1);
        } else if (lastDX == -1) {
          body.get(body.size() - 1).setCorner(2);
        }
        image = "headUp";
      }
      case down, keyBoardS -> {
        if (this.directionY == -1) {
          break;
        }
        // handle down
        setDirectionY(1);
        setDirectionX(0);
        if (lastDX == 1) {
          body.get(body.size() - 1).setCorner(1);
        } else if (lastDX == -1) {
          body.get(body.size() - 1).setCorner(- 2);
        }
        image = "headDown";
      }
    }
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
    for (int i = 0; i < INITIALIZE; i++) {
      if (i == 0) {
        body.add(new SnakeBody(this.getxPos(), this.getyPos() - (INITIALIZE) * this.getSize(), this.getSize(), null));
      } else {
        body.add(new SnakeBody(this.getxPos(), this.getyPos() - ((INITIALIZE - i) * this.getSize()), this.getSize(), null));
      }
    }
    image = "headDown";
  }
  public void draw() {
    for (int i = 0; i < body.size(); i++) {
      boolean isTail = false;
      int nextX;
      int nextY;
      if (i == 0) {
        isTail = true;
      }
      if (i == body.size() - 1) {
        nextX = (int) this.getxPos();
        nextY = (int) this.getyPos();
      } else {
        nextX = (int) body.get(i + 1).getxPos();
        nextY = (int) body.get(i + 1).getyPos();
      }
      float dx = (nextX - body.get(i).getxPos());
      float dy = (nextY - body.get(i).getyPos());

      if (dx >= 1) {
        dx = 1;
      }

      else if (dx <= -1) {
        dx = -1;
      }

      if (dy >= 1) {
        dy = 1;
      }
      else if (dy <= -1) {
        dy = -1;
      }

      body.get(i).setxPos(body.get(i).getxPos());
      body.get(i).setyPos(body.get(i).getyPos());
      body.get(i).draw(dx, dy, isTail);
    }
    getWindow().image(getImage(image),
            super.getxPos() + getWindow().getOffset(),
            super.getyPos(),
            super.getSize(),
            super.getSize());
  }

  public void grow() {
    this.body.add(0, new SnakeBody(body.get(0).getxPos(), body.get(0).getyPos(), this.getSize(), null));
  }

  public void moveBody(float prevX, float prevY) {
    if (this.directionX == 0 && this.directionY == 0) {
      return;
    }
    for (int i = 0; i < body.size(); i++) {
      body.get(i).setSizeX(this.getSize());
      body.get(i).setSizeY(this.getSize());
      if (i == body.size() - 1) {
        body.get(i).setxPos(prevX);
        body.get(i).setyPos(prevY);
        body.get(i).setCorner(0);
      } else {
        body.get(i).setxPos(body.get(i + 1).getxPos());
        body.get(i).setyPos(body.get(i + 1).getyPos());
        if (body.get(i + 1).isCorner() != 0) {
          body.get(i).setCorner(body.get(i + 1).isCorner());
          body.get(i + 1).setCorner(0);
        } else {
          body.get(i).setCorner(0);
        }
      }
    }
  }
}
