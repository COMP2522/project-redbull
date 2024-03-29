package org.Snake.UI;

import org.Snake.Window;

import java.util.ArrayList;

/**
 * Frame class which is meant to be a container for UIComponents
 *
 * @author
 * @version
 */
public class Frame extends UIComponent {
  /**
   * Boolean to check if the frame has been drawn for the first time
   */
  boolean firstDraw;
  /**
   * ArrayList of UIComponents
   */
  ArrayList<UIComponent> components;

  /**
   * Frame constructor which sets the position and size of the frame
   * @param parent the parent PApplet
   * @param x the x position of the frame
   * @param y the y position of the frame
   * @param width the width of the frame
   * @param height the height of the frame

   */

  /**
   * Frame constructor which sets the position and size of the frame
   * @param parent the parent PApplet
   * @param x the x position of the frame
   * @param y the y position of the frame
   * @param width the width of the frame
   * @param height the height of the frame
   */
  public Frame(Window parent, float x, float y, float width, float height) {
    super(parent, x, y, width, height);
    firstDraw = true;
    components = new ArrayList<>();
  }

  /**
   * Method to draw the frame
   */
  @Override
  public void draw() {
    if (firstDraw) {
      for (UIComponent component : components) {
        int y = component.getY();
        int x = component.getX();
        component.setY(y + this.y);
        component.setX(x + this.x);
      }
      firstDraw = false;
    }

    for (UIComponent component : components) {
      component.draw();
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
