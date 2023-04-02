package org.Snake;
import processing.core.PApplet;

public class Router extends UIComponent {

  // An array of pages that can be displayed by the Menu
  private UIComponent[] pages;

  // The currently active page
  private int activePageIndex;

  public Router(PApplet parent, float x, float y, float width, float height) {
    super(parent, x, y, width, height);
    this.pages = new UIComponent[] {
//            new HomePage(parent, x, y, width, height),
//            new AboutPage(parent, x, y, width, height),
//            new ContactPage(parent, x, y, width, height)
    };
    this.activePageIndex = 0; // Start with the first page active
  }

  @Override
  public void draw() {
    // Draw the active page
    pages[activePageIndex].draw();
  }

  @Override
  public void mouseClicked(float mx, float my) {
    // Check if any of the buttons on the active page were clicked
    pages[activePageIndex].mouseClicked(mx, my);

    // Check if the "next page" button was clicked
    if (nextPageButton.contains(mx, my)) {
      // Switch to the next page
      activePageIndex = (activePageIndex + 1) % pages.length;
    }

    // Check if the "previous page" button was clicked
    if (prevPageButton.contains(mx, my)) {
      // Switch to the previous page
      activePageIndex = (activePageIndex - 1 + pages.length) % pages.length;
    }
  }

  // Define the "next page" button
  private Button nextPageButton = new Button(parent, x + width - 80, y + height - 40, 70, 30, "Next") {
    @Override
    public void mouseClicked(float mx, float my) {
      // Do nothing, since the Menu handles the click
    }
  };

  // Define the "previous page" button
  private Button prevPageButton = new Button(parent, x + 10, y + height - 40, 70, 30, "Prev") {
    @Override
    public void mouseClicked(float mx, float my) {
      // Do nothing, since the Menu handles the click
    }
  };

}
