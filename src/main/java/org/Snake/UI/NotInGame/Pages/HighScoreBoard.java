package org.Snake.UI.NotInGame.Pages;
import org.Snake.UI.NotInGame.NotInGameUiManager;
import org.Snake.UI.Frame;
import org.Snake.UI.NotInGame.LevelButton;
import processing.core.PApplet;
import org.Snake.Database.KVPair;

import org.Snake.UI.Text;

import org.Snake.Database.MongoDb;

import java.util.ArrayList;

public class HighScoreBoard extends Frame {

  private final NotInGameUiManager uiManager;

  private ArrayList<KVPair> scores;

  public HighScoreBoard(PApplet parent, float x, float y, float width, float height, NotInGameUiManager uiManager) {
    super(parent, x, y, width, height, 0, "");
    this.uiManager = uiManager;
    scores = new ArrayList<>();
  }

  public void getScores() {
    MongoDb db = new MongoDb();
    scores = db.get(uiManager.getSelectedLevel());
    for (KVPair score : scores) {
      System.out.println(score.getKey() + " - " + score.getValue());
    }
  }

  @Override
  public void draw() {
    // Get the scores from the database
//    getScores();

    // Set up variables for displaying the scores
    int numScores = scores.size();
    int scoreHeight = 50;
    int startY = (int) (y + 100);
    int yGap = 10;

    // Loop through the scores and display them as Text objects
    for (int i = 0; i < numScores; i++) {
      KVPair score = scores.get(i);
      String scoreText = score.getKey() + " - " + score.getValue();
      Text scoreDisplay = new Text(parent, x + width/2, startY + i*(scoreHeight+yGap), scoreText);
      scoreDisplay.setTextSize(20);
      scoreDisplay.setTextColor(parent.color(255));
      scoreDisplay.draw();
    }
  }


  @Override
  public void mouseClicked(float mx, float my) {

  }
}
