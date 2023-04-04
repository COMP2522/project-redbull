package org.Snake.UI.Buttons;

import org.Snake.Sprite;
import org.Snake.Window;
import processing.core.PImage;

public class PlayButton extends Button{

  String[] images;

  public PlayButton(Window parent, float x, float y, float width, float height, String label, String[] images) {
    super(parent, x, y, width, height, label);
    this.images = images;
  }

  public PlayButton(Window parent, float x, float y, float width, float height, String label) {
    super(parent, x, y, width, height, label);
    this.images = images;
  }

  @Override
  public void draw() {
    // draw the image on the button
//    getParent().image(Sprite.getImage(images[0]), getX(), getY(), getWidth(), getHeight());
    super.draw();
  }

  @Override
  public void mouseClicked(float mx, float my) {
    if (contains(mx, my)) {
      System.out.println("Play button clicked");
    }
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
