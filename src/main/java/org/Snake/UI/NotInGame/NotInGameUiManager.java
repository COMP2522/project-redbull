package org.Snake.UI.NotInGame;
import org.Snake.Database.MongoDb;
import org.Snake.UI.Buttons.HighScoreButton;
import org.Snake.UI.Buttons.HomeButton;
import org.Snake.UI.Buttons.LevelButton;
import org.Snake.UI.Page;
import org.Snake.UI.Text;
import org.Snake.UI.UIComponent;
import org.Snake.Window;
import org.Snake.UI.Buttons.Button;
import org.Snake.UI.Buttons.PlayButton;

public class NotInGameUiManager extends UIComponent {

  // An array of pages that can be displayed by the Menu
  private Page[] pages;

  private String selectedLevel;

  boolean start;

  // The currently active page
  private int activePageIndex;

  private final Window window;

  public NotInGameUiManager(Window parent, float x, float y, float width, float height, MongoDb db, Window window) {
    super(parent, x, y, width, height);
    this.window = window;
    start = false;
    int sideBarWidth = 150;
    String[] levelNames = {"Cave", "Classic", "Modern", "FreeRoam", "Impossible!", "PacMan", "random", "placeholder", "placeholder2"};
    pages = new Page[4];
    this.activePageIndex = 0; // Start with the first page active

    selectedLevel = "none";

//    makeMenu();
//    makeSelectLevel(levelNames);
    makeHighScore();
  }

  private void makeSelectLevel(String[] levelNames) {
    pages[0] = new Page(parent, x , y , width , height);
    Page selectLevel = pages[0];
    int xOffset = 200; // adjust this value as needed
    int screenWidth = (int) getParent().getScreenSizeWidth() - xOffset;
    int screenHeight = (int) getParent().getScreenSizeHeight();
    int numRows = 3;
    int numCols = 3;
    int buttonWidth = screenWidth / numCols;
    int buttonHeight = screenHeight / numRows;

    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numCols; j++) {
        int levelIndex = i * numCols + j;
        if (levelIndex < levelNames.length) {
          selectLevel.add(new LevelButton(parent,
                  j * buttonWidth , i * buttonHeight,
                  buttonWidth, buttonHeight, levelNames[levelIndex]));
        }
      }
    }



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

  private void makeMenu() {
    pages[0] = new Page(parent, x , y , width , height);
    Page menu = pages[0];
    Text title = new Text(parent, (int) getParent().getScreenSizeWidth() / 2, (int) getParent().getScreenSizeHeight() / 4, "Project RedBull");
    title.setTextSize(100);
    menu.add(title);
    String[] leftImages = new String[4];
    for (int i = 0; i < 4; i++) {
      leftImages[i] = "src/main/java/org/Snake/UI/Images/frame_left_" + i + ".png";
    }

    ImageAnimation leftWings = new ImageAnimation(parent, (int) getParent().getScreenSizeWidth() / 2, (int) getParent().getScreenSizeHeight() / 4, 200, 200, leftImages, 100);

    menu.add(leftWings);

    String[] rightImages = new String[4];
    for (int i = 0; i < 4; i++) {
      rightImages[i] = "src/main/java/org/Snake/UI/Images/frame_right_" + i + ".png";
    }
    ImageAnimation rightWings = new ImageAnimation(parent, (int) getParent().getScreenSizeWidth() / 2, (int) getParent().getScreenSizeHeight() / 4, 200, 200, rightImages, 100);
    menu.add(rightWings);

    Button playButton = new PlayButton(parent, (int) getParent().getScreenSizeWidth() / 2, (int) getParent().getScreenSizeHeight() / 2, 200, 50, "Play");
    menu.add(playButton);

    Button highScoreButton = new HighScoreButton(parent, (int) getParent().getScreenSizeWidth() / 2, (int) getParent().getScreenSizeHeight() / 2 + 100, 200, 50, "Highscores");
    menu.add(highScoreButton);

    Button homeButton = new HomeButton(parent, (int) getParent().getScreenSizeWidth() / 2, (int) getParent().getScreenSizeHeight() / 2 + 200, 200, 50, "Home");
    menu.add(homeButton);
  }
}
