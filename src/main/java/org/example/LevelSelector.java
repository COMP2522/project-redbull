package org.example;

import processing.core.PApplet;

class LevelSelector extends UIComponent {

  private LevelButton[] levelButtons;

  private String levelNames[] = {"cave1", "classic", "modern", "level2", "ultraHard", "PacMan", "random", "placeholder", "placeholder2"};

  public LevelSelector(PApplet parent, float x, float y, float width, float height) {
    super(parent, x, y, width, height);
    levelButtons = new LevelButton[9];
    float buttonWidth = width / 3;
    float buttonHeight = height / 3;
    for (int i = 0; i < 9; i++) {
      float buttonX = x + buttonWidth * (i % 3);
      float buttonY = y + buttonHeight * PApplet.floor(i / 3);
      levelButtons[i] = new LevelButton(parent, buttonX, buttonY, buttonWidth, buttonHeight, levelNames[i]);
    }
  }

  @Override
  public void draw() {
    for (int i = 0; i < 9; i++) {
      levelButtons[i].draw();
    }
  }

  @Override
  public void mouseClicked(float mx, float my) {
    for (int i = 0; i < 9; i++) {
      if (levelButtons[i].contains(mx, my)) {
        levelButtons[i].setSelected(true);
        for (int j = 0; j < 9; j++) {
          if (i != j) {
            levelButtons[j].setSelected(false);
          }
        }
        break;
      }
    }
  }

  public String getSelectedLevel() {
    for (int i = 0; i < 9; i++) {
      if (levelButtons[i].isSelected()) {
        return levelButtons[i].getLabel();
      }
    }
    return "none";
  }

}

