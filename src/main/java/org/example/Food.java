package org.example;

public class Food extends Sprite {
    //Public tile constructor taking in x, y ,size, picture, and isWall
    private boolean isEaten = false;
    public Food(int xPos, int yPos, int size, String picture) {
        super(xPos, yPos, size, picture);
    }
    public boolean isEaten() {
        return isEaten;
    }
    public void eat() {
        isEaten = true;
    }
    public void draw() {
        if (!isEaten) {
            super.getWindow().image(super.food,
                    super.getxPos() + super.getWindow().getOffset(),
                    super.getyPos() + super.getWindow().getTopOffset(),
                    super.getSize(),
                    super.getSize());
        }
    }

    public void reset() {
        isEaten = false;
    }
}
