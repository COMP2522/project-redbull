//package test;
//
//import org.example.Window;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class WindowTest {
//
//  @Test
//  public void testGetWidth() {
//    // Arrange
//    Window window = new Window();
//
//    // Act
//    int width = window.getWidth();
//
//    // Assert
//    assertEquals(1080, width);
//  }
//
//  @Test
//  public void testGetHeight() {
//    // Arrange
//    Window window = new Window();
//
//    // Act
//    int height = window.getHeight();
//
//    // Assert
//    assertEquals(1080, height);
//  }
//
//  @Test
//  public void testGetOffset() {
//    // Arrange
//    Window window = new Window();
//
//    // Act
//    int offset = window.getOffset();
//
//    // Assert
//    assertEquals(0, offset);
//  }
//
//  @Test
//  public void testReset() {
//    // Arrange
//    Window window = new Window();
//
//    // Act
//    window.reset();
//
//    // Assert
//    assertEquals(0, window.lastKeyPressed);
//    assertEquals(0, window.spriteManager.getSnake().getBody().size());
//    assertFalse(window.clock.isPaused());
//  }
//}
