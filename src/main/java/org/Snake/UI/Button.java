package org.Snake.UI;

import org.Snake.Window;

public class Button extends UIComponent {
  /**
   * The label of the button
   */
  private String label;
  /**
   * Whether the button is pressed or not
   */
  private boolean pressed;

  /**
   * Constructor for the Button object
   * @param parent
   * @param x
   * @param y
   * @param width
   * @param height
   * @param label
   */
  public Button(Window parent, float x, float y, float width, float height, String label) {
    super(parent, x, y, width, height);
    this.label = label;
    this.pressed = false;
  }

  /**
   * Method to draw the button
   */
  @Override
  public void draw() {
    parent.stroke(0);
    if (pressed) {
      parent.fill(200, 0, 0);
    } else {
      parent.fill(255);
    }
    parent.rect(x, y, width, height);
    parent.fill(0);
    parent.text(label, x + width/2, y + height/2);
  }

  /**
   * Method for event handling
   * @param mx mouse x pos
   * @param my mouse y pos
   */
  @Override
  public void mouseClicked(float mx, float my) {
    if (contains(mx, my)) {
      pressed = true;

    } else {
      pressed = false;
    }
  }

  public boolean isPressed() {
    return pressed;
  }

  public void setLabel(String label) {
    this.label = label;
  }
}
