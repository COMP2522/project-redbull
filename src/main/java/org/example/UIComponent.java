package org.example;

import processing.core.PApplet;

public abstract class UIComponent {

  private PApplet parent;
  private float x;
  private float y;
  private float width;
  private float height;

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
      // Do something when the component is clicked
    }
  }
}
