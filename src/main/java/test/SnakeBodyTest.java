package test;

import org.junit.jupiter.api.BeforeEach;

import org.Snake.SnakeBody;


public class SnakeBodyTest {

// Instantiates a SnakeBody with the variable named snakeBody.
  SnakeBody snakeBody;


  /**
   * Sets up a new SnakeBody before each of the tests.
   * @throws Exception - If Null throw an exception.
   */
  @BeforeEach
  void setUp() throws Exception {
    snakeBody = new SnakeBody(100, 100, 20, "snake.png");

  }






}
