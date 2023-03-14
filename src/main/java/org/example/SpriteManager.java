package org.example;

import java.util.ArrayList;

//Sprite manager method to manage all on screen sprites
public class SpriteManager {
    private ArrayList<Sprite> sprites;
    private Snake player;
    private int tileWidth;

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

    public SpriteManager(Window window, int cellsize) {
        this.window = window;
        this.tileWidth = cellsize;
        createMaze();
        Sprite.setWindow(window);
        sprites = new ArrayList<>();
        player = Snake.getInstance(0, 10* tileWidth, tileWidth, null);
        sprites.add(player);
        //todo make this not O(n^2)
        for (Tile[] tile : tiles) {
            for (Tile tile1 : tile) {
                sprites.add(tile1);
            }
        }
    }
    public ArrayList<Sprite> update(int lastKeyPressed) {
        //MOVE PLAYER BASED TO KEY PRESS
        if (lastKeyPressed >= 37 && lastKeyPressed <= 40) {
            player.move(lastKeyPressed);
        }
        player.setxPos(player.getxPos() + player.getDirectionX()*this.tileWidth);
        player.setyPos(player.getyPos() + player.getDirectionY()*this.tileWidth);


        //update the sprites to the next frame
        this.collide();//before updating the sprites , check for collisions and update the sprites accordingly
        return sprites;
    }
    public void collide() {
        //calculate collisions
    }
    private void createMaze() {
        //create the maze

        // changed to be getting from a file later
//        this.cols = 50;

        this.tiles = new Tile[rows][cols];
//        tiles.add(new Tile(100, 200, 10, null, true));
    }
}
