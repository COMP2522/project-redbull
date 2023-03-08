package org.example;

import java.awt.*;

public class Tile extends Sprite{
    Image image;
    boolean isWall;
    public Tile(int xPos, int yPos, int size, Image picture, boolean isWall) {
        super(xPos, yPos, size, picture);
        this.isWall = isWall;
    }
}
