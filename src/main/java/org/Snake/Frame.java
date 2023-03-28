package org.Snake;

import processing.core.PApplet;

import javax.swing.table.TableRowSorter;
import java.util.ArrayList;

/**
 * Frame class which is meant to be a container for UIComponents
 */
public class Frame extends UIComponent {

  int padding;
  String direction;

  boolean firstDraw;

  ArrayList<UIComponent> components;

  /**
   * Frame constructor which sets the position and size of the frame
   * @param parent the parent PApplet
   * @param x the x position of the frame
   * @param y the y position of the frame
   * @param width the width of the frame
   * @param height the height of the frame
   * @param padding the padding of the frame
   * @param direction the direction of the frame
   */
  public Frame(PApplet parent, float x, float y, float width, float height, int padding, String direction) {
    super(parent, x, y, width, height);
    this.padding = padding;
    this.direction = direction;
    firstDraw = true;
    components = new ArrayList<>();
  }

  /**
   * Method to draw the frame
   */
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

  /**
   * Method for event handling
   */
  @Override
  public void mouseClicked(float mx, float my) {

  }

  /**
   * Method to add a UIComponent to the frame
   * @param scoreTracker the UIComponent to be added
   */
  public void add(UIComponent scoreTracker) {
    components.add(scoreTracker);
  }
}
