package org.Snake;

/**
 * Food class which is meant to be the food of the snake
 *
 * @author
 * @version 1.0
 */
public class Food extends Sprite {
    //Public tile constructor taking in x, y ,size, picture, and isWall
    private boolean isEaten = false;
    private String image;
    public Food(int xPos, int yPos, int size, String picture) {
        super(xPos, yPos, size);
        image = picture;
    }
    /**
     * Getter to return if the food has been eaten
     * @return true if the food has been eaten, false otherwise
     */
    public boolean isEaten() {
        return isEaten;
    }

    /**
     * Setter to set if the food has been eaten
     */
    public void eat() {
        isEaten = true;
    }

    /**
     * Method to draw the food
     */
    public void draw() {
        if (!isEaten) {
            super.getWindow().image(getImage(image),
                    super.getxPos() + super.getWindow().getOffset(),
                    super.getyPos() + super.getWindow().getTopOffset(),
                    super.getSize(),
                    super.getSize());
        }
    }

    /**
     * Method to reset the food
     */
    public void reset() {
        isEaten = false;
    }
}
