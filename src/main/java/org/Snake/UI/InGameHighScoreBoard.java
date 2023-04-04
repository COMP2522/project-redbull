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

  public void getScores() {
    System.out.println("Getting scores called");

    scores = db.get(UiManager.getInstance().getSelectedLevel());
    for (KVPair score : scores) {
      System.out.println(score.getKey() + " - " + score.getValue());
    }

    Text[] scoreTexts = new Text[scores.size()];
    for (int i = 0; i < scores.size(); i++) {
      scoreTexts[i] = new Text(parent, x + 100, y + 100 + (i * 50), scores.get(i).getKey() + " - " + scores.get(i).getValue());
    }
  }

  @Override
  public void draw() {
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

  }

  @Override
  public void add(UIComponent component) {
    super.add(component);
  }
}
