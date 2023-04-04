package org.Snake.UI;

import org.Snake.Window;

import java.util.ArrayList;

public class Page extends UIComponent {

  // so that everything can be shifted to the page's position
  boolean firstDraw;

  ArrayList<UIComponent> components;

  public Page(Window parent, float x, float y, float width, float height) {
    super(parent, x, y, width, height);
    firstDraw = true;
    components = new ArrayList<>();
  }

  @Override
  public void draw() {
    if (firstDraw) {
      for (int i = 0; i < components.size(); i++) {
        int y = components.get(i).getY();
        int x = components.get(i).getX();
        components.get(i).setY(y + this.y);
        components.get(i).setX(x + this.x);
      }
      firstDraw = false;
    }

    for (UIComponent component : components) {
      component.draw();
    }
  }

  @Override
  public void mouseClicked(float mx, float my) {
    for (UIComponent component : components) {
      component.mouseClicked(mx, my);
    }
  }

  public void add(UIComponent component) {
    components.add(component);
  }
}
