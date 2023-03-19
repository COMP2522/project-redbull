package org.example;

public class SnakeBody extends Sprite {

  private int rotation;

  //constructor
  public SnakeBody(int xPos, int yPos, int size, String picture) {
    super(xPos, yPos, size, picture);
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

  //method for moving the snake body
  public void move(int x, int y) {
    super.setxPos(x);
    super.setyPos(y);
  }

  //method for rotating the snake body
  public void rotate(int rotation) {
    this.rotation = rotation;
  }

  //method for


  public int getRotation() {
    return rotation;
  }

  public void setRotation(int rotation) {
    this.rotation = rotation;
  }
}
