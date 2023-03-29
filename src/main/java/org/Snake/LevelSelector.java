package org.Snake;

import processing.core.PApplet;




/**
 * LevelSelector class which is the main level selector
 *
 * @author
 * @version
 */
class LevelSelector extends UIComponent {





  private LevelButton[] levelButtons;

  //the names of the levels
  private String levelNames[] = {"Cave", "Classic", "Modern", "FreeRoam", "Impossible!", "PacMan", "random", "placeholder", "placeholder2"};

  private int numOfLevels = 9;
  /**
   * LevelSelector constructor which sets the position and size of the level selector
   * @param parent the parent PApplet
   * @param x the x position of the level selector
   * @param y the y position of the level selector
   * @param width the width of the level selector
   * @param height the height of the level selector
   */
  public LevelSelector(PApplet parent, float x, float y, float width, float height) {
    super(parent, x, y, width, height);

    levelButtons = new LevelButton[numOfLevels];
    float buttonWidth = width / 3;
    float buttonHeight = height / 3;
    for (int i = 0; i < numOfLevels; i++) {
      float buttonX = x + buttonWidth * (i % 3);
      float buttonY = y + buttonHeight * PApplet.floor(i / 3);


      levelButtons[i] = new LevelButton(parent, buttonX, buttonY, buttonWidth, buttonHeight, levelNames[i]);


    }
  }

  /**
   * Method to draw the level selector
   */
  @Override
  public void draw() {
    for (int i = 0; i < numOfLevels; i++) {
      levelButtons[i].draw();
    }
  }

  /**
   * Method for event handling
   * @param mx the x position of the mouse
   * @param my the y position of the mouse
   */
  @Override
  public void mouseClicked(float mx, float my) {
    for (int i = 0; i < numOfLevels; i++) {
      if (levelButtons[i].contains(mx, my)) {
        levelButtons[i].setSelected(true);
        for (int j = 0; j < numOfLevels; j++) {
          if (i != j) {
            levelButtons[j].setSelected(false);
          }
        }
        break;
      }
    }
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

