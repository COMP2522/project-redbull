package org.Snake.UI;

import org.Snake.Window;

public class ScoreDisplay extends UIComponent {
  /**
   * The parent window
   */
  private final Window parent;

  /**
   * Constructor for the ScoreDisplay object.
   * @param parent the parent window
   * @param x the x-coordinate of the score display
   * @param y the y-coordinate of the score display
   * @param width the width of the score display
   * @param height the height of the score display
   */
  public ScoreDisplay(Window parent, float x, float y, float width, float height) {
    super(parent, x, y, width, height);
    this.parent = parent;
  }

  /**
   * Method to draw the score display
   */
  @Override
  public void draw() {
    parent.pushStyle();
    parent.textSize(30);
    parent.fill(255,255,255);
    parent.text("Score: " + UiManager.getInstance().getScore(), getX(), getY());
    parent.popStyle();
  }/**
   * Method to handle mouse click events on the score display
   * @param mx the x-coordinate of the mouse
   * @param my the y-coordinate of the mouse
   */
  @Override
  public void mouseClicked(float mx, float my) {
    // do nothing
  }
/**
   * Method to get the width of the score display
   * @return the width of the score display
   */
  public float getWidth() {
    // get the width of the text
    return parent.textWidth("Score: " + UiManager.getInstance().getScore());
  }
}
