package org.example;

import java.util.ArrayList;

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
    private Window window;

    public SpriteManager(Window window) {
        createMaze();
        this.window = window;
        Sprite.setWindow(window);
        sprites = new ArrayList<>();
        player = Snake.getInstance(0, 100, 10, null);
        sprites.add(player);
        //todo make this not O(n^2)
        for (Tile[] tile : tiles) {
            for (Tile tile1 : tile) {
                sprites.add(tile1);
            }
        }
    }
    public ArrayList<Sprite> update() {
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
        this.cols = 50;

        // todo calculate tile width
        this.tileWidth = window.getWidth() / cols;

        this.tiles = new Tile[rows][cols];
//        tiles.add(new Tile(100, 200, 10, null, true));
    }
}
