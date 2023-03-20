package test;

import org.example.Tile;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The purpose of the tileTest class is to test the Tile constructor and ensure the boolean isWall are working
 * correctly.
 */
public class TileTest {

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
    Tile tile = new Tile(0, 0, 10, image, true);
    assertEquals(0, tile.getxPos());
    assertEquals(0, tile.getyPos());
    assertEquals(10, tile.getSize());
    assertEquals(image, tile.getPicture());
    assertTrue(tile.isWall());
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
    Tile tile1 = new Tile(0, 0, 10, image, true);
    assertTrue(tile1.isWall());

    Tile tile2 = new Tile(0, 0, 10, image, false);
    assertFalse(tile2.isWall());
  }

}
