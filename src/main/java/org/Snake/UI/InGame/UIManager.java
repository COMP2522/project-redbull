package org.Snake.UI.InGame;

//The imports for this class
import org.Snake.Database.MongoDb;
import org.Snake.Window;
import processing.core.PApplet;
import java.util.ArrayList;

/**
 * UIManager class
 */
public class UIManager {

  /*
  The selected level
   */
  private String selectedLevel;

  /*
  The array of "screens" we can display
   */
//  public ArrayList<Pages> Pages;

  /*
  The currently active page
   */
  private boolean start;

  // The currently active page
  private int activePageIndex;

  /*
  The parent PApplet
   */
  private final Window window;

  public UIManager(PApplet parent, float x, float y, float width, float height, MongoDb db, Window window) {
    this.window = window;
  }


  public void draw() {
    // Draw the active page
  }


  public void mouseClicked(float mx, float my) {
    // When the mouse was clicked in an area
  }

}
