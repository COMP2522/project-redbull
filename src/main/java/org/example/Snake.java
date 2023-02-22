package org.example;
import java.util.LinkedList;

/**
 * The Snake class. For snake things :)
 * Using the linkedlist library, makes the snake a singleton linked list
 */
public class Snake {


  private LinkedList<Scale> snakeTail;
  private static Snake instance;

  private Snake() {
    snakeTail = new LinkedList<>();
  }

  public static Snake getInstance() {
    if (instance == null) {
      instance = new Snake();
    }
    return instance;
  }

  /*
  The scale class of the snake
   */
  protected class Scale {
    private int x;
    private int y;

    public Scale(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public int getX() {
      return x;
    }

    public void setX(int x) {
      this.x = x;
    }

    public int getY() {
      return y;
    }

    public void setY(int y) {
      this.y = y;
    }

  }

}
