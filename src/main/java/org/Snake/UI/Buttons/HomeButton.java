package org.Snake.UI.Buttons;

import org.Snake.Window;

public class HomeButton extends Button{
  public HomeButton(Window parent, float x, float y, float width, float height, String label) {
    super(parent, x, y, width, height, label);
  }

  public HomeButton(Window parent, float x, float y, float width, float height, String label, String[] images) {
    super(parent, x, y, width, height, label, images);
  }

  @Override
  public void draw() {
    super.draw();
  }

  @Override
  public void mouseClicked(float mx, float my) {
    if (contains(mx, my)) {
      System.out.println("Home button clicked");
    }
  }

  @Override
  public void setLabel(String label) {
    super.setLabel(label);
  }
}
