package org.example;

import java.util.ArrayList;
import static java.lang.Math.round;
//Sprite manager method to manage all on screen sprites
public class SpriteManager {
    private ArrayList<Sprite> sprites;
    private Snake player;
    private int tileWidth;
    private boolean collided = false;

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    private int rows;
    private int cols;
    private Tile[][] tiles;
    private final Window window;

    public SpriteManager(Window window, int cellsize, int rows, int cols) {
        this.window = window;
        this.rows = rows;
        this.cols = cols;
        this.tileWidth = cellsize;
        createMaze();
        Sprite.setWindow(window);
        sprites = new ArrayList<>();
        player = Snake.getInstance(0*tileWidth, 1*tileWidth, tileWidth, null);
        sprites.add(player);
        //todo make this not O(n^2)
        for (Tile[] tile : tiles) {
            for (Tile tile1 : tile) {
                sprites.add(tile1);
            }
        }
    }
    public ArrayList<Sprite> animate(int framesPerClockTick) {

        //calculate the next position of the player
        float nextX = player.getxPos() + (player.getDirectionX()*(float) (this.tileWidth) / framesPerClockTick);
        float nextY = player.getyPos() + (player.getDirectionY()*(float) (this.tileWidth) / framesPerClockTick);

        player.setxPos(nextX);
        player.setyPos(nextY);

        return sprites;
    }
    public ArrayList<Sprite> update(int lastKeyPressed) {
//        if (player.getxPos() < 0 || player.getxPos() > this.cols * this.tileWidth || player.getyPos() < 0 || player.getyPos() > this.rows * this.tileWidth) {
//            this.collided = true;
//        }
        int trueX = round(player.getxPos() / this.tileWidth) * this.tileWidth;
        int trueY = round(player.getyPos() / this.tileWidth) * this.tileWidth;

        player.setxPos(trueX);
        player.setyPos(trueY);

        //MOVE PLAYER BASED TO KEY PRESS
        if (this.collided){
            player.setDirectionX(0);
            player.setDirectionY(0);
        }else
        if (lastKeyPressed >= 37 && lastKeyPressed <= 40) {
            player.move(lastKeyPressed);
        }

        //update the sprites to the next frame
//        this.collide();//before updating the sprites , check for collisions and update the sprites accordingly
        return sprites;
    }
    private void createMaze() {
        //create the maze

        // changed to be getting from a file later
//        this.cols = 50;

        this.tiles = new Tile[rows][cols];
//        tiles.add(new Tile(100, 200, 10, null, true));
    }


}
