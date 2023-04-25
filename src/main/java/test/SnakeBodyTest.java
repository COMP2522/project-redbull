package test;

// Import statements
import org.Snake.SnakeBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import processing.core.PImage;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The purpose of the SnakeBodyTest is to test the methods in the SnakeBody class and the functionality.
 *
 * @author adams
 */
@DisplayName("SnakeBody Tests")
class SnakeBodyTest {

  //Instantiating the snakebody variable.
  private SnakeBody snakeBody;

  // Sets up a new snakeBody before each test.
  @BeforeEach
  void setUp() {
    snakeBody = new SnakeBody(100f, 100f, 50, "testImage");
  }

  //Tests out getting the x size.
  @Test
  void testGetSizeX() {
    assertEquals(50, snakeBody.getSizeX());
  }

  //Tests out getting the y size.
  @Test
  void testGetSizeY() {
    assertEquals(50, snakeBody.getSizeY());
  }

  //Tests getting the picture.
  @Test
  void testGetPicture() {
    PImage testImage = new PImage();
    snakeBody.setPicture(testImage);
    assertEquals(testImage, snakeBody.getPicture());
  }

  // Tests setting a new image.
  @Test
  void testSetPicture() {
    PImage testImage = new PImage();
    snakeBody.setPicture(testImage);
    assertEquals(testImage, snakeBody.getPicture());
  }

  /**
   * The setSize will not update because only the size of both x and y can be changed together.
   * We should not be able to change only the x so the size should remain 50.
   */
  @Test
  void testSetSizeX() {
    snakeBody.setSizeX(60);
    // The getter here should still return 50 even though we tried setting
    assertEquals(50, snakeBody.getSizeX());
  }

  /**
   * The setSize will not update because only the size of both x and y can be changed together.
   * We should not be able to change only the y so the size should remain 50.
   */
  @Test
  void testSetSizeY() {
    snakeBody.setSizeY(60);
    //Getter should return 50 because we should not be able to set the size of the snake.
    assertEquals(50, snakeBody.getSizeY());
  }


  // Tests being able to set a corner body piece.
  @Test
  void testSetCorner() {
    snakeBody.setCorner(1);
    assertEquals(1, snakeBody.isCorner());
  }
}
