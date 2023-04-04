package org.Snake.UI.NotInGame.Pages;
import org.Snake.UI.UiManager;
import org.Snake.UI.Frame;
import org.Snake.UI.NotInGame.Button;
import org.Snake.UI.NotInGame.ImageAnimation;
import org.Snake.UI.Text;
import org.Snake.Window;
import org.Snake.UI.HomeButton;


public class MenuPage extends Frame {


  private final Button playButton;
  private final Button highScoreButton;

  public MenuPage(Window parent, float x, float y, float width, float height, int padding, String direction) {
    super(parent, x, y, width, height, padding, direction);

    // create the title
    Text title = new Text(parent, x + width / 2, y + height / 4, "Project RedBull");
    title.setTextSize(50);

    // create the play button
    int playButtonWidth = 500;

    playButton = new Button(parent, x + width/2 - playButtonWidth / 2, y + height/2, playButtonWidth, 75, "PLAY");

    int highScoreButtonWidth = 500;
    // create the high score button
    highScoreButton = new Button(parent, x + width/2 - highScoreButtonWidth / 2, y + height/2 + 100, highScoreButtonWidth, 75, "HIGH SCORES");

    HomeButton homeButton = new HomeButton(parent, x + 10, y + 10, 100, 100, "src/main/java/org/Snake/UI/Images/home.png");

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
    add(homeButton);
    add(wingsAnimationLeft);
    add(wingsAnimationRight);

  }

  @Override
  public void draw() {
    super.draw();
  }

  @Override
  public void mouseClicked(float mx, float my) {
    if (playButton.contains(mx, my)) {
      UiManager.getInstance().setPage("game");
    } else if (highScoreButton.contains(mx, my)) {
      UiManager.getInstance().setPage("highscore");
    }
  }
}
