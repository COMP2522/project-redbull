package org.example;

import java.awt.*;

public class Tile extends Sprite{
    Image image;
    boolean isWall;
    public Tile(int xPos, int yPos, int size, Image picture, Window window) {
        super(xPos, yPos, size, picture, window);
    }
}
