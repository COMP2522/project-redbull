package org.Snake.UI.NotInGame;

import org.Snake.UI.UIComponent;
import org.Snake.Window;
import processing.core.PApplet;

public class LevelButton extends UIComponent {

  private final String label;
  private boolean selected;

  /**
   * Button constructor which sets the position and size of the button
   * @param parent the parent PApplet
   * @param x the x position of the button
   * @param y the y position of the button
   * @param width the width of the button
   * @param height the height of the button
   * @param label the label of the button
   */
  public LevelButton(Window parent, float x, float y, float width, float height, String label) {
    super(parent, x, y, width, height);
    this.label = label;
    this.selected = false;
  }

  /**
   * Method to draw the button
   */
  @Override
  public void draw() {
    getParent().rectMode(PApplet.CORNER);
    if (contains(getParent().mouseX, getParent().mouseY)) {
      // if the mouse is hovering over the button, use a slightly darker fill color
      getParent().fill(selected ? 180 : 230);
    } else {
      getParent().fill(selected ? 200 : 255);
    }
    getParent().stroke(100);
    getParent().rect(x, y, width, height, 3);
    getParent().fill(0);
    getParent().textAlign(PApplet.CENTER, PApplet.CENTER);
    getParent().text(label, x + width / 2, y + height / 2);
  }

  /**
   * Method for event handling UI clicks
   */
  @Override
  public void mouseClicked(float mx, float my) {
    if (contains(mx, my)) {
      setSelected(!selected);
    }
  }

  /**
   * Method to set the button to be selected
   */
  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  /**
   * Method to get the label of the button
   */
  public String getLabel() {
    return label;
  }
}
