package org.example;

import java.awt.*;

//Tile class which represents the movable tiles and is a Sprite
public class Tile extends Sprite{
    Image image;
    boolean isWall;
    //Public tile constructor taking in x, y ,size, picture, and isWall
    public Tile(int xPos, int yPos, int size, Image picture, boolean isWall) {
        super(xPos, yPos, size, picture);
        this.isWall = isWall;
    }
}
