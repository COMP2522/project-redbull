package org.Snake;

//Tile class which represents the movable tiles and is a Sprite
public class Tile extends Sprite{
    private boolean wall;
    //Public tile constructor taking in x, y ,size, picture, and isWall
    public Tile(int xPos, int yPos, int size, String picture, boolean wall) {
        super(xPos, yPos, size, picture);
        this.wall = wall;
    }

    public boolean isWall() {
        return wall;
    }

    public void draw(){
//        super.getWindow().stroke(0,0,0);
//        super.getWindow().pushStyle();
//        super.getWindow().fill(0, 204, 204);
//        super.getWindow().rect(super.getxPos()+super.getWindow().getOffset(),
//                super.getyPos()+super.getWindow().getTopOffset(),
//                super.getSize(),
//                super.getSize());
        try {
            super.getWindow().image(super.wall,
                    super.getxPos()+super.getWindow().getOffset(),
                    super.getyPos()+super.getWindow().getTopOffset(),
                    super.getSize(),
                    super.getSize());
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
//        super.getWindow().popStyle();
    }
}
