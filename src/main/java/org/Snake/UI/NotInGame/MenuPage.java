package org.Snake.UI.NotInGame;

import org.Snake.UI.Frame;
import org.Snake.UI.Text;
import processing.core.PApplet;

public class MenuPage extends Frame {

  NotInGameUiManager notInGameUiManager;

  private Button playButton;
  private Button highScoreButton;
  private Text title;

  public MenuPage(PApplet parent, float x, float y, float width, float height, int padding, String direction, NotInGameUiManager notInGameUiManager) {
    super(parent, x, y, width, height, padding, direction);

    // create the title
    title = new Text(parent, x + width/2, y + height/4, "Project RedBull");
    title.setTextSize(50);

    // create the play button
    playButton = new Button(parent, x + width/2, y + height/2, 200, 75, "PLAY");

    // create the high score button
    highScoreButton = new Button(parent, x + width/2, y + height/2 + 100, 200, 75, "HIGH SCORES");

    // create wings animation
    String[] left = new String[4];
    for (int i = 0 ; i < 4; i++) {
      left[i] = "src/main/java/org/Snake/UI/Images/frame_left_" + i + ".png";
    }
    ImageAnimation wingsAnimationLeft = new ImageAnimation(parent, 277, 70, 200, 200, left, 100);

    String[] right = new String[4];
    for (int i = 0 ; i < 4; i++) {
      right[i] = "src/main/java/org/Snake/UI/Images/frame_right_" + i + ".png";
    }
    ImageAnimation wingsAnimationRight = new ImageAnimation(parent, 800, 70, 200, 200, right, 100);

    // add the components to the frame
    add(title);
    add(playButton);
    add(highScoreButton);
    add(wingsAnimationLeft);
    add(wingsAnimationRight);

    this.notInGameUiManager = notInGameUiManager;
  }

  @Override
  public void draw() {
    super.draw();
  }

  @Override
  public void mouseClicked(float mx, float my) {
    System.out.println("Mouse clicked at " + mx + ", " + my);
    if (playButton.contains(mx, my)) {
      notInGameUiManager.setPage("game");
    } else if (highScoreButton.contains(mx, my)) {
      System.out.println("High score button clicked");
    }
  }
}
