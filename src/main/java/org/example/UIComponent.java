package org.example;

import processing.core.PApplet;

public abstract class UIComponent {

  protected PApplet parent;
  protected float x;
  protected float y;
  protected float width;
  protected float height;

  public UIComponent(PApplet parent, float x, float y, float width, float height) {
    this.parent = parent;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public abstract void draw();

  public boolean contains(float mx, float my) {
    return mx >= x && mx <= x + width && my >= y && my <= y + height;
  }

  public void mouseClicked(float mx, float my) {
    if (contains(mx, my)) {
      System.out.println("Clicked on UIComponent");
    }
  }

  protected PApplet getParent() {
    return parent;
  }
}
