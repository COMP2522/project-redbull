package test;

import org.Snake.Kale_Doesnt_know_how_to_Code_Exception;
import org.Snake.Snake;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// TODO For refactoring setup a before each that instantiates a new snake.
public class SnakeTest {


  @Test
  public void testSnakeInitialization() {

    // Creating a new instance of snake for the test.
    Snake snake = Snake.getInstance(5, 2, 3, null);

    // Testing that snake has 3 for size as given in the constructor
    assertEquals(3, snake.getSize());

    // Testing that snake has 5 for the x position as given in the constructor
    assertEquals(5, snake.getxPos());


    // Testing that snake has 2 for the y position as given in the constructor
    assertEquals(2, snake.getyPos());

    // Testing that snake has null for the picture as given in the constructor
    assertNull(snake.getPicture());


  }


  // Tests getting the speed of the snake.
  @Test
  void getSpeedTest() {
    Snake snake = Snake.getInstance(5, 2, 3, null);
    int expectedSpeed = 0;
    assertEquals(expectedSpeed, snake.getSpeed());

  }


  // Testing being able to set the speed of the snake.
  @Test
  void setSpeedTest() {
    Snake snake = Snake.getInstance(5, 2, 3, null);
    int expectedSpeed = 10;
    snake.setSpeed(expectedSpeed);
    assertEquals(expectedSpeed, snake.getSpeed());
  }


  // Checking that the snake is able to move right represented by the 1 for the x direction.
  @Test
  void setDirectionXTest() {
    Snake snake = Snake.getInstance(5, 2, 3, null);
    int expectedDirectionX = 1;
    snake.setDirectionX(expectedDirectionX);
    assertEquals(expectedDirectionX, snake.getDirectionX());
  }


  // Tests that the snake is able to move down represented by the -1.
  @Test
  void setDirectionYTest() {
    Snake snake = Snake.getInstance(5, 2, 3, null);
    int expectedDirectionY = -1;
    snake.setDirectionY(expectedDirectionY);
    assertEquals(expectedDirectionY, snake.getDirectionY());
  }


}