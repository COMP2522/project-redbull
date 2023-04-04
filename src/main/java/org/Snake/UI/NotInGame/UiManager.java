package org.Snake.UI.NotInGame;

import org.Snake.Database.MongoDb;
import org.Snake.UI.NotInGame.Pages.HighScoreBoard;
import org.Snake.UI.NotInGame.Pages.LevelSelector;
import org.Snake.UI.NotInGame.Pages.MenuPage;
import org.Snake.UI.UIComponent;
import org.Snake.Window;
import org.Snake.UI.NotInGame.Pages.HighScoreLevels;

public class UiManager {

  // An array of pages that can be displayed by the Menu
  private UIComponent[] pages;

  private String selectedLevel;

  boolean start;

  // The currently active page
  private int activePageIndex;

  private final Window window;

  private static UiManager instance;

  private UiManager(Window parent, float width, float height, MongoDb db) {

    this.window = parent;
    start = false;
    int sideBarWidth = 150;
    String[] levelNames = {"Cave", "Classic", "Modern", "FreeRoam", "Impossible!", "PacMan", "random", "BatCave", "PI"};
    int x = 0;
    int y = 0;
    this.pages = new UIComponent[] {
            new MenuPage(parent, x , y , width , height , 0, "", this),
            new LevelSelector(parent, x + sideBarWidth, y, width- sideBarWidth, height, levelNames, this),
            new HighScoreLevels(parent, x + sideBarWidth, y, width - sideBarWidth, height, levelNames, this),
            new HighScoreBoard(parent, x, y, width, height, this, db)
    };
    this.activePageIndex = 0; // Start with the first page active

    selectedLevel = "none";
  }

  public static UiManager getInstance(Window parent, float width, float height, MongoDb db) {
    if (instance == null) {
      instance = new UiManager(parent, width, height, db);
    }
    return instance;
  }

  public static UiManager getInstance() {
    return instance;
  }

  public void draw() {
    // Draw the active page
    pages[activePageIndex].draw();
  }

  public void mouseClicked(float mx, float my) {
    // Check if any of the buttons on the active page were clicked
    pages[activePageIndex].mouseClicked(mx, my);
  }

  public void setPage(String game) {
    if (game.equals("game")) {
      activePageIndex = 1;
    } else if (game.equals("highscore")) {
      activePageIndex = 2;
    } else if (game.equals("highScoreBoard")) {
      activePageIndex = 3;
      ((HighScoreBoard) pages[activePageIndex]).getScores();
    } else if (game.equals("menu")){ // home
      activePageIndex = 0;
    }
  }

  public String getSelectedLevel() {
    return selectedLevel;
  }

  public void setSelectedLevel(String label) {
    this.selectedLevel = label;
  }

  public void setStart(boolean start) {
    this.start = start;
  }

  public boolean getStart() {
    return start;
  }
}
