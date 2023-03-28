package org.Snake;

import processing.core.PApplet;

import javax.swing.*;
import java.util.ArrayList;

public class InGameUI extends UIComponent {

  public ArrayList<UIComponent> components;
  Frame testFrame;
  private ScoreTracker scoreTracker;
  public InGameUI(PApplet parent, float x, float y, float width, float height) {
    super(parent, x, y, width, height);
    scoreTracker = new ScoreTracker(getParent(), 0f,0f, 100f,100f);
    components = new ArrayList<>();
    testFrame = new Frame(getParent(), 100,100,100,100,10,"right");
    testFrame.add(scoreTracker);
    components.add(testFrame);
  }

  @Override
  public void draw() {
    // draw over all elements with a black box

    for (UIComponent elem : components) {
      parent.pushStyle();
      parent.fill(0);
      parent.noStroke();
      parent.rect(elem.getX() - elem.getX() / 2f, elem.getY() - elem.getY() / 2f, elem.width, elem.height);
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
