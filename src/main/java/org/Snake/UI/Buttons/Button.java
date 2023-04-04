package org.Snake.UI.Buttons;

import org.Snake.UI.UIComponent;
import org.Snake.Window;

public class Button extends UIComponent {

  private String label;
  private boolean pressed;

  private String[] images;

  public Button(Window parent, float x, float y, float width, float height, String label) {
    super(parent, x, y, width, height);
    this.label = label;
    this.pressed = false;
  }

  public Button(Window parent, float x, float y, float width, float height, String label, String[] images) {
    super(parent, x, y, width, height);
    this.label = label;
    this.pressed = false;
    this.images = images;
  }

  @Override
  public void draw() {
    //    getParent().image(Sprite.getImage(images[0]), getX(), getY(), getWidth(), getHeight());
    parent.stroke(0);
    if (pressed) {
      parent.fill(200, 0, 0);
    } else {
      parent.fill(255);
    }
    parent.rect(x, y, width, height);
    parent.fill(0);

    parent.textSize(40);
    // change the text color to white
    parent.fill(255,0,0);
    parent.text(label, x , y + height / 2 + 10);
  }

  @Override
  public void mouseClicked(float mx, float my) {

  }

  public void setLabel(String label) {
    this.label = label;
  }
}
