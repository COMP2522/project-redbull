package org.example;

import processing.core.PApplet;

public class LevelButton extends UIComponent {

  private String label;
  private boolean selected;

  public LevelButton(PApplet parent, float x, float y, float width, float height, String label) {
    super(parent, x, y, width, height);
    this.label = label;
    this.selected = false;
  }

  @Override
  public void draw() {
    getParent().rectMode(PApplet.CORNER);
    getParent().fill(selected ? 200 : 255);
    getParent().stroke(0);
    getParent().rect(x, y, width, height, 5);
    getParent().fill(0);
    getParent().textAlign(PApplet.CENTER, PApplet.CENTER);
    getParent().text(label, x + width / 2, y + height / 2);
  }

  @Override
  public void mouseClicked(float mx, float my) {
    if (contains(mx, my)) {
      setSelected(!selected);
    }
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }

}
