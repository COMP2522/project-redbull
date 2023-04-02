package org.Snake;

/**
 * Tile class which represents the movable tiles and is a Sprite
 *
 * @author
 * @version
 */
public class Wall extends Sprite{
    private final boolean wall;
    private final String image;
    //Public tile constructor taking in x, y ,size, picture, and isWall
    public Wall(int xPos, int yPos, int size, String picture, boolean wall) {
        super(xPos, yPos, size);
        this.wall = wall;
        this.image = picture;
    }

    /**
     * The method that determines if the Tile is a wall
     * @return
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
