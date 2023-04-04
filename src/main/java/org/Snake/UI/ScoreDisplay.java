package org.Snake.UI;

import org.Snake.Window;

public class ScoreDisplay extends UIComponent {

  private final Window parent;

  public ScoreDisplay(Window parent, float x, float y, float width, float height) {
    super(parent, x, y, width, height);
    this.parent = parent;
  }

  @Override
  public void draw() {
    parent.pushStyle();
    parent.textSize(30);
    parent.fill(255,255,255);
    parent.text("Score: " + UiManager.getInstance().getScore(), getX(), getY());
    parent.popStyle();
  }
  @Override
  public void mouseClicked(float mx, float my) {
    // do nothing
  }
}
