package org.example;

import java.util.ArrayList;

public class SpriteManager {
    private ArrayList<Sprite> sprites;
    private Snake player = Snake.getInstance(0, 0, 0, null, null);
    private ArrayList<Tile> tiles;
    private Window window;
    public SpriteManager(Window window) {
        this.window = window;
        sprites = new ArrayList<>();
        tiles = new ArrayList<>();
    }
    public ArrayList<Sprite> update() {
        //update the sprites to the next frame
        this.collide();//before updating the sprites , check for collisions and update the sprites accordingly

        return sprites;
    }
    public void collide() {
        //calculate collisions
    }
    public ArrayList<Tile> createMaze() {
        //create the maze and add it to the sprites arraylist
        return tiles;
    }
}
