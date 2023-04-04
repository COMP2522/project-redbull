package org.Snake.UI.InGame;

import org.Snake.UI.UIComponent;
import org.Snake.Window;
import processing.core.PApplet;

/**
 * ScoreTracker class which is meant to be a UIComponent that tracks the score
 *
 * @author
 * @version
 */
public class ScoreTracker extends UIComponent {
  private int score;

  private final PApplet parent;

  /**
   * ScoreTracker constructor which sets the position and size of the ScoreTracker
   * @param parent the parent PApplet
   * @param x the x position of the ScoreTracker
   * @param y the y position of the ScoreTracker
   * @param width the width of the ScoreTracker
   * @param height the height of the ScoreTracker
   */
  public ScoreTracker(Window parent, float x, float y, float width, float height) {
    super(parent, x, y, width, height);
    this.parent = parent;
    this.setScore(0);
  }

  /**
   * Method to draw the ScoreTracker
   */
  @Override
  public void draw() {
    parent.pushStyle();
    parent.textSize(30);
    parent.fill(255,255,255);
    parent.text("Score: " + getScore(), getX(), getY());
    parent.popStyle();
  }

  /**
   * Method for event handling
   */
  @Override
  public void mouseClicked(float mx, float my) {
  }


  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }
}
