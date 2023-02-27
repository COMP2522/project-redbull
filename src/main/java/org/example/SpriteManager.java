package org.example;

import java.util.ArrayList;

public class SpriteManager {
    private ArrayList<Sprite> sprites;
    private Snake player;
    private ArrayList<Tile> tiles;
    private Window window;
    public SpriteManager(Window window) {
        createMaze();
        this.window = window;
        Sprite.setWindow(window);
        sprites = new ArrayList<>();
        player = Snake.getInstance(0, 100, 10, null);
        sprites.add(player);
        for (Tile tile : tiles) {
            sprites.add(tile);
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
        this.tiles = new ArrayList<>();
        tiles.add(new Tile(100, 200, 10, null, true));
    }
}
