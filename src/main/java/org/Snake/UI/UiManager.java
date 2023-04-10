package org.Snake.UI;

import org.Snake.Database.MongoDb;
import org.Snake.UI.NotInGame.Pages.*;
import org.Snake.UI.UIComponent;
import org.Snake.Window;

public class UiManager {

  // An array of pages that can be displayed by the Menu
  private UIComponent[] pages;

  private String selectedLevel;

  boolean start;

  // The currently active page
  private int activePageIndex;

  private final Window window;
  /**
   * The instance of the UiManager
   */
  private static UiManager instance;

  private int score; // The score of the player

  /**
   * Method to get the score of the player
   * @param parent the parent window
   * @param width the width of the window
   * @param height the height of the window
   * @param db the database
   */
  private UiManager(Window parent, float width, float height, MongoDb db) {

    this.window = parent;
    start = false;
    int sideBarWidth = 150;
    String[] levelNames = {"Cave", "Classic", "Modern", "FreeRoam", "Impossible!", "PacMan", "random", "BatCave", "Beetles"};
    int x = 0;
    int y = 0;

    this.score = 0; // Set the score to 0

    this.pages = new UIComponent[] {
            new MenuPage(parent, x , y , width , height , 0, ""),
            new LevelSelector(parent, x + sideBarWidth, y, width- sideBarWidth, height, levelNames),
            new HighScoreLevels(parent, x + sideBarWidth, y, width - sideBarWidth, height, levelNames),
            new HighScoreBoard(parent, x, y, width, height, db),
            new InGameUI(parent, x, y, width, height)
    };
    this.activePageIndex = 0; // Start with the first page active

    selectedLevel = "none";
  }

  /**
   * Method to get the instance of the UiManager
   * @param parent the parent window
   * @param width  the width of the window
   * @param height the height of the window
   * @param db the database
   * @return the instance of the UiManager
   */
  public static UiManager getInstance(Window parent, float width, float height, MongoDb db) {
    if (instance == null) {
      instance = new UiManager(parent, width, height, db);
    }
    return instance;
  }

  public static UiManager getInstance() {
    return instance;
  }

  /**
   * Method to draw the active page.
   */
  public void draw() {
    // Draw the active page
    if (!window.isGameActive()) {
      window.background(0);
    }
    pages[activePageIndex].draw();
  }

  /**
   * Method to handle mouse click events on the active page.
   * @param mx the x-coordinate of the mouse
   * @param my the y-coordinate of the mouse
   */
  public void mouseClicked(float mx, float my) {
    // Check if any of the buttons on the active page were clicked

    if (!window.isGameActive()) {
      window.background(0);
    }

    pages[activePageIndex].mouseClicked(mx, my);
  }

  /**
   * Method to handle mouse move events on the active page.
   * @param game
   */
  public void setPage(String game) {

    if (!game.equals("inGame")) {
      window.setGameActive(false);
      window.background(0);
    }

    if (game.equals("game")) {
      activePageIndex = 1;
    } else if (game.equals("highscore")) {
      activePageIndex = 2;
    } else if (game.equals("highScoreBoard")) {
      activePageIndex = 3;
      ((HighScoreBoard) pages[activePageIndex]).getScores();
    } else if (game.equals("menu")){ // home
      activePageIndex = 0;
    } else if (game.equals("inGame")) { // ingame
      activePageIndex = 4;
      ((InGameUI) pages[activePageIndex]).getScores();

    }
  }

  /**
   * Method to get the selected level
   * @return the selected level
   */
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

  public void incrementScore() {
    score++;
  }

  public int getScore() {
    return score;
  }

  public void resetScore() {
    this.score = 0 ;
  }

  public Window getWindow() {
    return window;
  }

  /**
   * Method to redraw the highscore board on update.
   */
  public void reDrawHighScores() {
    ((HighScoreBoard) pages[3]).getScores();

  }
}
