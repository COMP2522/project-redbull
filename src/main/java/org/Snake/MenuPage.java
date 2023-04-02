package org.Snake;

import processing.core.PApplet;

public class MenuPage extends Frame {

  private Button playButton;
  private Button highScoreButton;
  private Text title;

  public MenuPage(PApplet parent, float x, float y, float width, float height, int padding, String direction) {
    super(parent, x, y, width, height, padding, direction);

    // create the title
    title = new Text(parent, x + width/2, y + height/4, "Snake Game");
    title.setTextSize(50);

    // create the play button
    playButton = new Button(parent, x + width/2, y + height/2, 200, 75, "PLAY");
    playButton.setOnClickListener(() -> {
      // handle click event here
      System.out.println("Play button clicked");
    });

    // create the high score button
    highScoreButton = new Button(parent, x + width/2, y + height/2 + 100, 200, 75, "HIGH SCORES");
    highScoreButton.setOnClickListener(() -> {
      // handle click event here
      System.out.println("High score button clicked");
    });

    // add the components to the frame
    add(title);
    add(playButton);
    add(highScoreButton);
  }

  @Override
  public void draw() {
    super.draw();
  }

  @Override
  public void mouseClicked(float mx, float my) {
    super.mouseClicked(mx, my);
  }
}
