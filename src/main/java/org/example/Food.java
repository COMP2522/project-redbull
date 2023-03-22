package org.example;

public class Food extends Sprite {
    //Public tile constructor taking in x, y ,size, picture, and isWall
    public Food(int xPos, int yPos, int size, String picture) {
        super(xPos, yPos, size, picture);
    }

    public void draw() {
        super.getWindow().image(super.getPicture(),
                super.getxPos() + super.getWindow().getOffset(),
                super.getyPos() + super.getWindow().getTopOffset(),
                super.getSize(),
                super.getSize());
    }
}
