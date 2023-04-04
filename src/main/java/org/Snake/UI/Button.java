package org.Snake.UI;

import org.Snake.UI.UIComponent;
import processing.core.PApplet;

public class Button extends UIComponent {

  private String label;
  private boolean pressed;

  public Button(PApplet parent, float x, float y, float width, float height, String label) {
    super(parent, x, y, width, height);
    this.label = label;
    this.pressed = false;
  }

  @Override
  public void draw() {
//    parent.stroke(0);
//    if (pressed) {
//      parent.fill(200, 0, 0);
//    } else {
//      parent.fill(255);
//    }
//    parent.rect(x, y, width, height);
//    parent.fill(0);
//    parent.text(label, x + width/2, y + height/2);
  }

  @Override
  public void mouseClicked(float mx, float my) {
//    if (contains(mx, my)) {
//      pressed = true;
//    } else {
//      pressed = false;
//    }
  }

  public boolean isPressed() {
    return pressed;
  }

  public void setLabel(String label) {
    this.label = label;
  }
}
