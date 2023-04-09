package org.Snake.UI;

import org.Snake.Database.KVPair;
import org.Snake.Database.MongoDb;
import org.Snake.Window;

import java.util.ArrayList;

public class InGameHighScoreBoard extends Frame {
  /**
   *  KVPair arraylist to store the scores
   */
  private ArrayList<KVPair> scores;
  /**
   * MongoDb object to get the scores from the database
   */
  private final MongoDb db;

  /**
   * Constructor for the InGameHighScoreBoard
   * @param parent the parent PApplet
   * @param x the x position of the frame
   * @param y the y position of the frame
   * @param width the width of the frame
   * @param height the height of the frame
   */
  public InGameHighScoreBoard(Window parent, float x, float y, float width, float height) {
    super(parent, x, y, width, height);
    this.db = new MongoDb();
  }

  /**
   * Method to get the scores from the database
   */
  public void setScores() {
    System.out.println("Getting scores called");
    scores = db.get(UiManager.getInstance().getSelectedLevel());
  }

  @Override
  public void draw() {
    // every 500 draws update the scores
//    if (parent.frameCount % 500 == 0) {
//      setScores();
//    }
    // Draw HighScores title
    Text highScoresTitle = new Text(parent, x + width/2, y + height/10, "HighScores");
    highScoresTitle.setTextSize(32);
    highScoresTitle.setTextColor(parent.color(255));
    highScoresTitle.draw();

    // Draw level name
    Text levelName = new Text(parent, x + width/2, y + height/5, UiManager.getInstance().getSelectedLevel());
    levelName.setTextSize(24);
    levelName.setTextColor(parent.color(255));
    levelName.draw();
    // Draw scores
    float scoreY = y + height/3;
    for (KVPair score : scores) {
      String scoreText = score.getKey() + " - " + score.getValue();
      Text scoreItem = new Text(parent, x + width/2, scoreY, scoreText);
      scoreItem.setTextSize(20);
      scoreItem.setTextColor(parent.color(255));
      scoreItem.draw();
      scoreY += 30;
    }
//    System.out.println(scores);
  }

  /**
   * Method to handle mouse click events on the InGameHighScoreBoard
   * @param component the UIComponent to be added
   */
  @Override
  public void add(UIComponent component) {
    super.add(component);
  }
}
