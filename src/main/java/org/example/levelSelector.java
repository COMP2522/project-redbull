package org.example;

import processing.core.PApplet;

public class levelSelector extends UIComponent {

  String display;

  public levelSelector(PApplet parent, float x, float y, float width, float height) {
    super(parent, x, y, width, height);
    display = "this";
  }

  @Override
  public void draw() {
    super.getParent().rectMode(PApplet.CORNER);
    super.getParent().fill(255, 255, 255);
    super.getParent().rect(x, y, width, height);
    super.getParent().fill(0, 0, 0);
    super.getParent().textAlign(PApplet.CENTER, PApplet.CENTER);
    super.getParent().text(display, x + width / 2, y + height / 2);
  }

  @Override
  public void mouseClicked(float mx, float my) {
    {
      if (contains(mx, my)) {
        this.display = "that";
      }
    }
  }
}
