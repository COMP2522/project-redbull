package org.Snake.UI.NotInGame;
import org.Snake.UI.NotInGame.Pages.HighScoreBoard;
import org.Snake.UI.NotInGame.Pages.LevelSelector;
import org.Snake.UI.NotInGame.Pages.MenuPage;
import org.Snake.UI.UIComponent;
import processing.core.PApplet;
import org.Snake.UI.NotInGame.Pages.HighScoreLevels;
public class NotInGameUiManager extends UIComponent {

  // An array of pages that can be displayed by the Menu
  private UIComponent[] pages;

  private String selectedLevel;

  boolean start;

  // The currently active page
  private int activePageIndex;

  public NotInGameUiManager(PApplet parent, float x, float y, float width, float height) {
    super(parent, x, y, width, height);
    start = false;
    String[] levelNames = {"Cave", "Classic", "Modern", "FreeRoam", "Impossible!", "PacMan", "random", "placeholder", "placeholder2"};
    this.pages = new UIComponent[] {
            new MenuPage(parent, x, y, width, height, 0, "", this),
            new LevelSelector(parent, x, y, width, height, levelNames, this),
            new HighScoreLevels(parent, x, y, width, height, levelNames, this),
            new HighScoreBoard(parent, x, y, width, height, this)
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

    // Check if the "next page" button was clicked
    if (nextPageButton.contains(mx, my)) {
      // Switch to the next page
      activePageIndex = (activePageIndex + 1) % pages.length;
    }

    // Check if the "previous page" button was clicked
    if (prevPageButton.contains(mx, my)) {
      // Switch to the previous page
      activePageIndex = (activePageIndex - 1 + pages.length) % pages.length;
    }
  }

  // Define the "next page" button
  private Button nextPageButton = new Button(parent, x + width - 80, y + height - 40, 70, 30, "Next") {
    @Override
    public void mouseClicked(float mx, float my) {
      // Do nothing, since the Menu handles the click
    }
  };

  // Define the "previous page" button
  private Button prevPageButton = new Button(parent, x + 10, y + height - 40, 70, 30, "Prev") {
    @Override
    public void mouseClicked(float mx, float my) {
      // Do nothing, since the Menu handles the click
    }
  };

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
