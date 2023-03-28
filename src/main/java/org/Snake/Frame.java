package org.Snake;

import processing.core.PApplet;

import javax.swing.table.TableRowSorter;
import java.util.ArrayList;

public class Frame extends UIComponent {

  int padding;
  String direction;

  boolean firstDraw;

  ArrayList<UIComponent> components;

  public Frame(PApplet parent, float x, float y, float width, float height, int padding, String direction) {
    super(parent, x, y, width, height);
    this.padding = padding;
    this.direction = direction;
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

    for (int i = 0; i < components.size(); i++) {
      components.get(i).draw();
    }
  }

  @Override
  public void mouseClicked(float mx, float my) {

  }

  public void add(UIComponent scoreTracker) {
    components.add(scoreTracker);
  }
}
