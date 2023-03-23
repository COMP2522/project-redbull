package org.Snake;

import processing.core.PApplet;

public class ScoreTracker extends UIComponent{
  private int score;

  private PApplet parent;

  public ScoreTracker(PApplet parent, float x, float y, float width, float height) {
    super(parent, x, y, width, height);
    this.parent = parent;
    this.score = 0;
  }

  @Override
  public void draw() {
    parent.pushStyle();
    parent.textSize(30);
    parent.fill(255,255,255);
    parent.text("Score: " + score, getX(), getY());
    parent.popStyle();
  }

  @Override
  public void mouseClicked(float mx, float my) {

  }

  public void incrementScore() {
    score++;
  }

  public void resetScore() {
    this.score = 0;
  }
}
