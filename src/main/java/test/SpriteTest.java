package test;

import org.Snake.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import processing.core.PImage;


/**
 * The purpose of the SpriteTest class is to give coverage to the Sprite class and verify that general functionality
 * works as intended.
 */
class SpriteTest {

  // testConstructor ensures that the values put inside the Sprite constructor are accurately stored.
  @Test
  void testConstructor() {
    int xPos = 10;
    int yPos = 20;
    int size = 3;
    Sprite sprite = new Sprite(xPos, yPos, size);
    Assertions.assertEquals(xPos, sprite.getxPos(), 1e-15);
    Assertions.assertEquals(yPos, sprite.getyPos(), 1e-15);
    Assertions.assertEquals(size, sprite.getSize());
    Assertions.assertNull(sprite.getPicture()); // getPicture is looks for type pImage and not a string value.
  }

  // The setter test ensures that we can set the values for sprite.
  @Test
  void testSetters() {
    Sprite sprite = new Sprite(0, 0, 0);
    int xPos = 10;
    int yPos = 20;
    int size = 30;
    PImage picture = new PImage();
    sprite.setxPos(xPos);
    sprite.setyPos(yPos);
    sprite.setSize(size);
    sprite.setPicture(picture);
    Assertions.assertEquals(xPos, sprite.getxPos(), 1e-15);
    Assertions.assertEquals(yPos, sprite.getyPos(), 1e-15);
    Assertions.assertEquals(size, sprite.getSize());
    Assertions.assertEquals(picture, sprite.getPicture());
  }
}