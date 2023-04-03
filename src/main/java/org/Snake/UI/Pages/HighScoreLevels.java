package org.Snake.UI.Pages;
import org.Snake.UI.HomeButton;
import org.Snake.UI.NotInGame.NotInGameUiManager;
import org.Snake.UI.Frame;
import org.Snake.UI.NotInGame.LevelButton;
import processing.core.PApplet;

public class HighScoreLevels extends Frame {

  private final LevelButton[] levelButtons;
  private final int numOfLevels;
  private final NotInGameUiManager uiManager;
  private  HomeButton homeButton;


  /**
   * Frame constructor which sets the position and size of the frame
   *
   * @param parent    the parent PApplet
   * @param x         the x position of the frame
   * @param y         the y position of the frame
   * @param width     the width of the frame
   * @param height    the height of the frame
   * @param uiManager
   */
  public HighScoreLevels(PApplet parent, float x, float y, float width, float height, String[] levelNames, NotInGameUiManager uiManager) {
    super(parent, x, y, width, height, 0,"");

    numOfLevels = levelNames.length;
    this.uiManager = uiManager;
    levelButtons = new LevelButton[numOfLevels];
    float buttonWidth = width / 3;
    float buttonHeight = height / 3;
    for (int i = 0; i < numOfLevels; i++) {
      float buttonX = x + buttonWidth * (i % 3);
      float buttonY = y + buttonHeight * PApplet.floor(i / 3);
      levelButtons[i] = new LevelButton(parent, buttonX, buttonY, buttonWidth, buttonHeight, levelNames[i]);
    }
    homeButton = new HomeButton(parent, x - 90, y + 10, 100, 100, "src/main/java/org/Snake/UI/Images/home.png", uiManager);

  }

  @Override
  public void draw() {
    for (int i = 0; i < numOfLevels; i++) {
      levelButtons[i].draw();
    }
    homeButton.draw();
  }

  @Override
  public void mouseClicked(float mx, float my) {
    System.out.print("mouse clicked");
    for (int i = 0; i < numOfLevels; i++) {
      if (levelButtons[i].contains(mx, my)) {
        levelButtons[i].setSelected(true);
        for (int j = 0; j < numOfLevels; j++) {
          if (i != j) {
            levelButtons[j].setSelected(false);
          }
        }
        uiManager.setStart(false);
        uiManager.setSelectedLevel(levelButtons[i].getLabel());
        uiManager.setPage("highScoreBoard");

        break;
      }
    }
    homeButton.mouseClicked(mx, my);
  }

  /**
   * Method to get the selected level
   * @return the buttons for the level
   */
  public String getSelectedLevel() {
    for (int i = 0; i < numOfLevels; i++) {
      if (levelButtons[i].isSelected()) {
        return levelButtons[i].getLabel();
      }
    }
    return "none";
  }

}
