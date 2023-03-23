package org.Snake;

import processing.core.PApplet;

import java.util.ArrayList;

public class InGameUI extends UIComponent {

  public ArrayList<UIComponent> components;
  private DisplayScore scoreTracker;
  public InGameUI(PApplet parent, float x, float y, float width, float height) {
    super(parent, x, y, width, height);
    scoreTracker = new DisplayScore(getParent(), 100f,10f, 100f,100f);
    components = new ArrayList<>();
    components.add(scoreTracker);
  }

  @Override
  public void draw() {
    // draw over all elements with a black box

    for (UIComponent elem : components) {
      parent.pushStyle();
      parent.fill(0);
      parent.noStroke();
      parent.rect(elem.getX() - (elem.getX() / 2f + 10), elem.getY() - (elem.getY() / 2f + 10), elem.width + 20, elem.height + 10);
      parent.popStyle();
      elem.draw();
    }
  }

  @Override
  public void mouseClicked(float mx, float my) {

  }

  public void incrementScore() {
    scoreTracker.incrementScore();
  }

  public void resetScore() {
    scoreTracker.resetScore();
  }
}
