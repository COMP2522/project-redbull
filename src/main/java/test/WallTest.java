package test;

import org.Snake.Wall;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The purpose of the tileTest class is to test the Tile constructor and ensure the boolean isWall are working
 * correctly.
 */
public class WallTest {

  @Test
  public void testConstructor() {
    String image = new String();
//    Image image = new String() {
//      @Override
//      public int getWidth(ImageObserver imageObserver) {
//        return 0;
//      }
//
//      @Override
//      public int getHeight(ImageObserver imageObserver) {
//        return 0;
//      }
//
//      @Override
//      public ImageProducer getSource() {
//        return null;
//      }
//
//      @Override
//      public Graphics getGraphics() {
//        return null;
//      }
//
//      @Override
//      public Object getProperty(String s, ImageObserver imageObserver) {
//        return null;
//      }
//    };
    Wall wall = new Wall(0, 0, 10, image, true);
    assertEquals(0, wall.getxPos());
    assertEquals(0, wall.getyPos());
    assertEquals(10, wall.getSize());
    assertEquals(image, wall.getPicture());
    assertTrue(wall.isWall());
  }

  @Test
  public void testIsWall() {
    String image = new String();
//    Image image = new String() {
//      @Override
//      public int getWidth(ImageObserver imageObserver) {
//        return 0;
//      }
//
//      @Override
//      public int getHeight(ImageObserver imageObserver) {
//        return 0;
//      }
//
//      @Override
//      public ImageProducer getSource() {
//        return null;
//      }
//
//      @Override
//      public Graphics getGraphics() {
//        return null;
//      }
//
//      @Override
//      public Object getProperty(String s, ImageObserver imageObserver) {
//        return null;
//      }
//    };
    Wall wall1 = new Wall(0, 0, 10, image, true);
    assertTrue(wall1.isWall());

    Wall wall2 = new Wall(0, 0, 10, image, false);
    assertFalse(wall2.isWall());
  }

}
