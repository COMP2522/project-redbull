package org.Snake.UI;

import org.Snake.UI.NotInGame.NotInGameUiManager;
import processing.core.PApplet;
import processing.core.PImage;


/**
 * A home button UI component that extends the UIComponent class
 */
public class HomeButton extends UIComponent {

  private final PImage image;

  private NotInGameUiManager nG;

  /**
   * Initializes a new instance of the HomeButton class.
   *
   * @param parent    The parent PApplet object.
   * @param x         The x position of the button.
   * @param y         The y position of the button.
   * @param width     The width of the button.
   * @param height    The height of the button.
   * @param imagePath The path to the image used for the button.
   * @param nG
   */
  public HomeButton(PApplet parent, float x, float y, float width, float height, String imagePath, NotInGameUiManager nG) {
    super(parent, x, y, width, height);
    this.nG = nG;

    // Load the image from the file path
    image = parent.loadImage(imagePath);
  }

  /**
   * Draws the button on the screen.
   */
  @Override
  public void draw() {
    // Draw the image at the button's position and size
    getParent().image(image, getX(), getY(), getWidth(), getHeight());
  }

  /**
   * Called when the mouse is clicked on the button.
   * @param mx The x position of the mouse click.
   * @param my The y position of the mouse click.
   */
  @Override
  public void mouseClicked(float mx, float my) {
    // Check if the mouse click is within the button's bounds
    if (contains(mx, my)) {
      // Handle the button click here
      // ...
      System.out.print("Home button clicked");
      nG.setPage("menu");
    }
  }
}
