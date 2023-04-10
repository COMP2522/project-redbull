package org.Snake.UI;

import org.Snake.Sound;
import org.Snake.UI.UiManager;
import org.Snake.Window;
import processing.core.PImage;


/**
 * A home button UI component that extends the UIComponent class
 *
 * @author
 * @version
 */
public class HomeButton extends UIComponent {

  private final PImage image;

  /**
   * Initializes a new instance of the HomeButton class.
   *
   * @param parent    The parent PApplet object.
   * @param x         The x position of the button.
   * @param y         The y position of the button.
   * @param width     The width of the button.
   * @param height    The height of the button.
   * @param imagePath The path to the image used for the button.
   */
  public HomeButton(Window parent, float x, float y, float width, float height, String imagePath) {
    super(parent, x, y, width, height);

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
      UiManager.getInstance().setSelectedLevel("none");
      System.out.print("Home button clicked");
      UiManager.getInstance().setPage("menu");
      Sound.stopSound();
    }
  }
}
