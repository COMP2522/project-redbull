package org.Snake;

import processing.core.PApplet;
import java.util.ArrayList;

public class Frame extends UIComponent {

  private ArrayList<UIComponent> components;
  private float padding;

  public Frame(PApplet parent, float x, float y, float width, float height, float padding) {
    super(parent, x, y, width, height);
    this.components = new ArrayList<UIComponent>();
    this.padding = padding;
  }

  public void push(UIComponent component) {
    components.add(component);
  }

  public void draw() {
    parent.rect(x, y, width, height);
    float currentY = y + padding;
    for (UIComponent component : components) {
      component.setX(x + padding);
      component.setY(currentY);
      component.draw();
      currentY += component.getHeight() + padding;
    }
  }

  public void mouseClicked(float mx, float my) {
    for (UIComponent component : components) {
      if (component.contains(mx, my)) {
        component.mouseClicked(mx, my);
      }
    }
  }

}
