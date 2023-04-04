package org.Snake.UI.InGame;

//The imports for this class
import org.Snake.Database.MongoDb;
import org.Snake.UI.Page;
import org.Snake.UI.Pages.HighScoreBoard;
import org.Snake.UI.Pages.HighScoreLevels;
import org.Snake.UI.Pages.LevelSelector;
import org.Snake.UI.Pages.MenuPage;
import org.Snake.UI.UIComponent;
import org.Snake.Window;
import processing.core.PApplet;
import java.util.ArrayList;


/**
 * UIManager class
 */
public class UIManager {

  /**
   * Thge names of the levels
   */
  private String[]  levelNames = {"Cave", "Classic", "Modern", "FreeRoam", "Impossible!", "PacMan", "random", "placeholder", "placeholder2"};

  /**
   * The parent PApplet
   */
  protected PApplet parent;

  /*
  The selected level
   */
  private String selectedLevel;

  /*
  The array of "screens" we can display
   */
  public ArrayList<Page> Pages;

  /*
  The currently active page
   */
  private boolean start;

  // The currently active page
  private int activePageIndex;

  /*
  The parent PApplet
   */
  private Window window;

  //temporary shit from the other UI classes
  public ArrayList<UIComponent> components;
  //  Frame testFrame;
  private ScoreTracker scoreTracker;


  /**
   * Constructor
   * @param parent
   * @param x
   * @param y
   * @param width
   * @param height
   * @param db
   * @param window
   */
  public UIManager(PApplet parent, float x, float y, float width, float height, MongoDb db, Window window) {
    this.window = window;
    start = false;
    selectedLevel = "none";

    //Add the pages to the array
    Pages = new ArrayList<Page>();


    //NotIngGameUi, but with arraylist
    int sideBarWidth = 150;

    this.components = new UIComponent[]{
          new MenuPage(parent, x , y , width , height , 0, ""),
          new LevelSelector(parent, x+ sideBarWidth, y, width- sideBarWidth, height, levelNames),
          new HighScoreLevels(parent, x+ sideBarWidth, y, width - sideBarWidth, height, levelNames),
          new HighScoreBoard(parent, x, y, width, height, db)
    };


    //Shit from th InGameUi
    scoreTracker = new ScoreTracker(parent, 0f,0f, 100f,100f);
    components = new ArrayList<>();

  }

  public void setPage(String game) {
    if (game.equals("game")) { //this is the level selector
      activePageIndex = 1;
    } else if (game.equals("highscore")) {
      activePageIndex = 2;
    } else if (game.equals("highScoreBoard")) {
      activePageIndex = 3;
    } else if (game.equals("menu")) {
      activePageIndex = 0;
    } else if (game.equals("InGame")){
      activePageIndex = 4;
    }
  }

  /**
   *
   */
  public void draw() {
    // Draw the active page
    Pages.get(activePageIndex).draw();

    //in game UI
    for (UIComponent elem : components) {
      parent.pushStyle();
      parent.fill(0);
      parent.noStroke();
      parent.rect(elem.getX() - elem.getX() / 2f, elem.getY() - elem.getY() / 2f, elem.getWidth(), elem.getHeight());
      parent.popStyle();
      elem.draw();
    }

  }


  //Shit from InGameUi
  public void incrementScore() {
    scoreTracker.setScore(scoreTracker.getScore() + 1);
  }
  public void resetScore() {
    scoreTracker.setScore(0);
  }
  public int getScore() {
    return scoreTracker.getScore();
  }


  /**
   *
   * @param mx
   * @param my
   */
  public void mouseClicked(float mx, float my) {
    // When the mouse was clicked in an area
    //for the menu page


  }

  /**
   *
   * @return
   */
  public String getSelectedLevel() {

    return selectedLevel;
  }

  /**
   *
   * @param label
   */
  public void setSelectedLevel(String label) {

    this.selectedLevel = label;
  }

  /**
   *
   * @param start
   */
  public void setStart(boolean start) {

    this.start = start;
  }

  /**
   *
   * @return
   */
  public boolean getStart() {

    return start;
  }

}
