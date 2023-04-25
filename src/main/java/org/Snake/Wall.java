package org.Snake;

/**
 * Tile class which represents the movable tiles and is a Sprite
 *
 * @author
 * @version 1.0
 */
public class Wall extends Sprite{

    /**
     * The boolean that determines if the Tile is a wall
     */
    private final boolean wall;

    /**
     * The image of the Tile
     */
    private final String image;

    /**
     * The getter for the image
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * Public tile constructor taking in x, y ,size, picture, and isWall
     * @param xPos the x position of the tile
     * @param yPos the y position of the tile
     * @param size the size of the tile
     * @param picture the image of the tile
     * @param wall the boolean that determines if the tile is a wall
     */
    public Wall(int xPos, int yPos, int size, String picture, boolean wall) {
        super(xPos, yPos, size);
        this.wall = wall;
        this.image = picture;
    }

    /**
     * The method that determines if the Tile is a wall
     * @return true if the Tile is a wall, false otherwise
     */
    public boolean isWall() {
        return wall;
    }

    /**
     * The method that draws the tiles
     */
    public void draw(){
        try {
            getWindow().image(super.getImage(image),
                    getxPos() + getWindow().getOffset(),
                    getyPos()+ getWindow().getTopOffset(),
                    getSize(),
                    getSize());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
