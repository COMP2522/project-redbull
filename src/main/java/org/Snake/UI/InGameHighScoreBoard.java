package org.Snake.UI;

import org.Snake.Database.KVPair;
import org.Snake.Database.MongoDb;
import org.Snake.Window;

import java.util.ArrayList;

public class InGameHighScoreBoard extends Frame {

  private ArrayList<KVPair> scores;

  private final MongoDb db;

  public InGameHighScoreBoard(Window parent, float x, float y, float width, float height) {
    super(parent, x, y, width, height);
    this.db = new MongoDb();
  }

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

  @Override
  public void add(UIComponent component) {
    super.add(component);
  }
}
