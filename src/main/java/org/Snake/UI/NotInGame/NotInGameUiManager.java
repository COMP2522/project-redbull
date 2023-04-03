package org.Snake.UI.NotInGame;
import org.Snake.Database.MongoDb;
import org.Snake.UI.HomeButton;
import org.Snake.UI.NotInGame.Pages.HighScoreBoard;
import org.Snake.UI.NotInGame.Pages.LevelSelector;
import org.Snake.UI.NotInGame.Pages.MenuPage;
import org.Snake.UI.UIComponent;
import org.Snake.Window;
import processing.core.PApplet;
import org.Snake.UI.NotInGame.Pages.HighScoreLevels;



public class NotInGameUiManager extends UIComponent {

  // An array of pages that can be displayed by the Menu
  private UIComponent[] pages;

  private String selectedLevel;

  boolean start;

  // The currently active page
  private int activePageIndex;

  private final Window window;

  public NotInGameUiManager(PApplet parent, float x, float y, float width, float height, MongoDb db, Window window) {
    super(parent, x, y, width, height);
    this.window = window;
    start = false;
    String[] levelNames = {"Cave", "Classic", "Modern", "FreeRoam", "Impossible!", "PacMan", "random", "placeholder", "placeholder2"};
    this.pages = new UIComponent[] {
            new MenuPage(parent, x , y , width , height , 0, "", this),
            new LevelSelector(parent, x+ 100, y, width- 100, height, levelNames, this),
            new HighScoreLevels(parent, x, y, width, height, levelNames, this),
            new HighScoreBoard(parent, x, y, width, height, this, db)
    };
    this.activePageIndex = 0; // Start with the first page active

    selectedLevel = "none";
  }

  @Override
  public void draw() {
    // Draw the active page
    pages[activePageIndex].draw();
  }

  @Override
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
