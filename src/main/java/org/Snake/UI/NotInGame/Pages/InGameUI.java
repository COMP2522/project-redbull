package org.Snake.UI.NotInGame.Pages;

import org.Snake.UI.Frame;
import org.Snake.UI.ScoreDisplay;
import org.Snake.UI.UIComponent;
import org.Snake.Window;
import org.Snake.UI.HomeButton;
import org.Snake.UI.InGameHighScoreBoard;

import java.util.ArrayList;

/**
 * InGameUI class which is meant to be the UI for the game
 */
public class InGameUI extends UIComponent {

  // list of all UI elements
  public ArrayList<UIComponent> components;
  Frame testFrame;

  ScoreDisplay scoreDisplay;
  private final InGameHighScoreBoard highScoreBoard;

  /**
   * InGameUI constructor which sets the position and size of the UI
   *
   * @param parent    the parent PApplet
   * @param x         the x position of the UI
   * @param y         the y position of the UI
   * @param width     the width of the UI
   * @param height    the height of the UI
   */
  public InGameUI(Window parent, float x, float y, float width, float height) {
    super(parent, x, y, width, height);
     scoreDisplay = new ScoreDisplay(getParent(), 0f, 200f, 200f, 200f);
    components = new ArrayList<>();
    testFrame = new Frame(getParent(), 100,100,100,100);
    testFrame.add(scoreDisplay);
    components.add(testFrame);
    HomeButton homeButton = new HomeButton(parent, x + 10, y + 10, 100, 100, "src/main/java/org/Snake/UI/Images/home.png");
    components.add(homeButton);

    highScoreBoard = new InGameHighScoreBoard(parent, parent.getScreenWidth() - 200, 10, 200, 900);
    components.add(highScoreBoard);
  }

  /**
   * Method to draw the UI
   */
  @Override
  public void draw() {
    // draw over all elements with a black box

    int x = scoreDisplay.getX() - scoreDisplay.getX() / 2 - 10;
    int y = (int) (scoreDisplay.getY() - scoreDisplay.getWidth() / 4);
    float width = scoreDisplay.getWidth();
    float height = scoreDisplay.getHeight() / 3;

    parent.fill(0);
    parent.noStroke();
    parent.rect(x, y, width, height);

    x = highScoreBoard.getX();
    y = (int) (highScoreBoard.getY());
    width = highScoreBoard.getWidth();
    height = highScoreBoard.getHeight() ;

    parent.fill(0);
    parent.noStroke();
    parent.rect(x, y, width, height);

    for (UIComponent elem : components) {
      elem.draw();
    }

  }

  @Override
  public void mouseClicked(float mx, float my) {
    for (UIComponent elem : components) {
      elem.mouseClicked(mx, my);
    }
  }

  public void getScores() {
    highScoreBoard.setScores();
  }
}
