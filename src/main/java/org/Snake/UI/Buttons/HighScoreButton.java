package org.Snake.UI.Buttons;

import org.Snake.Window;

public class HighScoreButton extends Button{

  public HighScoreButton(Window parent, float x, float y, float width, float height, String label) {
    super(parent, x, y, width, height, label);
  }

  @Override
  public void draw() {
    super.draw();
  }

  @Override
  public void mouseClicked(float mx, float my) {
    if (contains(mx, my)) {
      System.out.println("HighScore button clicked");
    }
  }

  @Override
  public void setLabel(String label) {
    super.setLabel(label);
  }

  @Override
  public boolean contains(float mx, float my) {
    return super.contains(mx, my);
  }

  @Override
  protected Window getParent() {
    return super.getParent();
  }

  @Override
  public int getX() {
    return super.getX();
  }

  @Override
  public int getY() {
    return super.getY();
  }

  @Override
  protected void setY(float v) {
    super.setY(v);
  }

  @Override
  protected void setX(float v) {
    super.setX(v);
  }


}
