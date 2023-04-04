package org.Snake.UI;

import org.Snake.Window;
import processing.core.PApplet;

import java.awt.*;

public class Text extends UIComponent {



  private String content;
  private int textSize;
  private int textColor;
  private int width;
  private int height;


  public void setWidth(int width) {
    this.width = width;
  }

  @Override
  public float getWidth() {
    return parent.getWidth();
  }

  public void setHeight(int height) {
    this.height = height;
  }

  /**
   * Constructor for the Text object
   * @param parent the parent PApplet
   * @param x the x-coordinate of the text
   * @param y the y-coordinate of the text
   * @param content the text content to display
   */
  public Text(Window parent, float x, float y, String content) {
    super(parent, x, y, parent.textWidth(content), parent.textAscent() + parent.textDescent());
    this.content = content;
    this.textSize = 16;
    this.textColor = parent.color(255);
  }

  /**
   * Method to draw the Text object on the screen
   */
  @Override
  public void draw() {
    parent.pushStyle();
    parent.fill(textColor);
    parent.textSize(textSize);
    parent.textAlign(PApplet.CENTER, PApplet.CENTER);
    parent.text(content, x, y);
    parent.popStyle();
  }

  /**
   * Method to handle mouse click events on the Text object
   */
  @Override
  public void mouseClicked(float mx, float my) {
    // Do nothing
  }

  /**
   * Set the text size for the Text object
   * @param textSize the new text size to set
   */
  public void setTextSize(int textSize) {
    this.textSize = textSize;
  }

  /**
   * Set the text color for the Text object
   * @param textColor the new text color to set
   */
  public void setTextColor(int textColor) {
    this.textColor = textColor;
  }
  public String getContent() {
    return content;
  }

  public void setFontSize(int i) {
    this.textSize = i;
  }

  public void setFillColor(Color black) {
    this.textColor = parent.color(0);
  }
}
