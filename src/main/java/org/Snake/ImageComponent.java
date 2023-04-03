package org.Snake;

import org.Snake.UI.UIComponent;
import processing.core.PApplet;

public abstract class ImageComponent extends UIComponent {
  /**
   * Initializing UI component variables
   *
   * @param parent PApplet
   * @param x      float
   * @param y      float
   * @param width  float
   * @param height float
   */
  public ImageComponent(PApplet parent, float x, float y, float width, float height) {
    super(parent, x, y, width, height);


 this.parent = parent;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
}

  //Abstract draw method to be used on any onscreen elements
  public abstract void draw();

  public boolean contains(float mx, float my) {
    return mx >= x && mx <= x + width && my >= y && my <= y + height;
  }

  //Abstract Mouse clicked method to check for any mouse click with x and y pos
  public abstract void mouseClicked(float mx, float my);

  /**
   * getter for parent PApplet element
   * @return parent
   */
  protected PApplet getParent() {
    return parent;
  }

  //Get X Value
  public int getX() {
    return (int) x;
  }

  //Get Y Value
  public int getY() {
    return (int) y;
  }

  //Set Y Value
  protected void setY(float v) {
    this.y = v;
  }

  //Set X Value
  protected void setX(float v) {
    this.x = v;
  }
}

