package org.Snake.UI.InGame;

import org.Snake.UI.Page;
import org.Snake.UI.UIComponent;
import org.Snake.Window;
import org.Snake.UI.NotInGame.NotInGameUiManager;

import java.util.ArrayList;

/**
 * InGameUI class which is meant to be the UI for the game
 */
public class InGameUI extends UIComponent {

  // list of all UI elements
  public ArrayList<UIComponent> components;
  Page testFrame;
  private ScoreTracker scoreTracker;
  private NotInGameUiManager uiManager;

  /**
   * InGameUI constructor which sets the position and size of the UI
   *
   * @param parent    the parent PApplet
   * @param x         the x position of the UI
   * @param y         the y position of the UI
   * @param width     the width of the UI
   * @param height    the height of the UI
   * @param uiManager
   */
  public InGameUI(Window parent, float x, float y, float width, float height, NotInGameUiManager uiManager) {
    super(parent, x, y, width, height);
    this.uiManager = uiManager;
    scoreTracker = new ScoreTracker(getParent(), 0f,0f, 100f,100f);
    components = new ArrayList<>();
    testFrame = new Page(getParent(), 100,100,100,100);
    testFrame.add(scoreTracker);
    components.add(testFrame);
//    homeButton = new HomeButton(parent, x - 90, y + 10, 100, 100, "src/main/java/org/Snake/UI/Images/home.png", this.uiManager);
//    components.add(homeButton);
  }

  /**
   * Method to draw the UI
   */
  @Override
  public void draw() {
    // draw over all elements with a black box

    for (UIComponent elem : components) {
      parent.pushStyle();
      parent.fill(0);
      parent.noStroke();
      parent.rect(elem.getX() - elem.getX() / 2f, elem.getY() - elem.getY() / 2f, elem.getWidth(), elem.getHeight());
      parent.popStyle();
      elem.draw();
    }
  }

  /**
   * Method for event handling
   *
   * @author
   * @version
   */
  @Override
  public void mouseClicked(float mx, float my) {
    for (UIComponent elem : components) {
      elem.mouseClicked(mx, my);
    }
  }

  /**
   * Method to increase the score
   */
  public void incrementScore() {
    scoreTracker.setScore(scoreTracker.getScore() + 1);
  }

  /**
   * Method to reset the score
   */
  public void resetScore() {
    scoreTracker.setScore(0);
  }

  public int getScore() {
    return scoreTracker.getScore();
  }
}
