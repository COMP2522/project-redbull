package org.example;

import java.io.File;
import java.util.ArrayList;
import static java.lang.Math.round;

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
    private String wallImage = "src" + File.separator + "main" + File.separator + "images" + File.separator + "wall.png";
    private String snakeImage = "src" + File.separator + "main" + File.separator + "images" + File.separator + "snakeDown.png";
    private String bodyImage = "src" + File.separator + "main" + File.separator + "images"  + File.separator + "bodyNS.png";
    private String tailImage = "src" + File.separator + "main" + File.separator + "images"  + File.separator + "tailUp.png";

    public SpriteManager(Window window, int cellsize, int rows, int cols) {
        this.window = window;
        this.rows = rows;
        this.cols = cols;
        this.tileWidth = cellsize;
        Sprite.setWindow(window);
        createMaze();
        sprites = new ArrayList<>();
        player = Snake.getInstance(5*tileWidth, (int) (5*tileWidth+ window.getTopOffset()), tileWidth, snakeImage);
        SnakeBody body1 =  new SnakeBody(5*tileWidth, (int) (4*tileWidth+ window.getTopOffset()), tileWidth, bodyImage);
        SnakeBody body2 =  new SnakeBody(5*tileWidth, (int) (3*tileWidth+ window.getTopOffset()), tileWidth, bodyImage);
        SnakeBody tail = new SnakeBody(5*tileWidth, (int) (2*tileWidth+ window.getTopOffset()), tileWidth, tailImage);
        sprites.add(player);
        sprites.add(body1);
        sprites.add(body2);
        sprites.add(tail);

        player.grow(body1);
        player.grow(body2);
        player.grow(tail);

        //todo make this not O(n^2)
        for (Tile[] tile : tiles) {
            for (Tile tile1 : tile) {
                sprites.add(tile1);
            }
        }
    }
    public ArrayList<Sprite> animate() {

        //calculate the next position of the player
        float nextX = player.getxPos() + (player.getDirectionX()*(this.tileWidth) / Clock.getFramesPerClock());
        float nextY = player.getyPos() + (player.getDirectionY()* (this.tileWidth) / Clock.getFramesPerClock());
        float prevX = player.getxPos();
        float prevY = player.getyPos();

        player.setxPos(nextX);
        player.setyPos(nextY);
//        player.slither(prevX, prevY);

        return sprites;
    }
    public ArrayList<Sprite> update(int lastKeyPressed){
        //update the sprites to the next frame
        int trueX = round(player.getxPos() / this.tileWidth) * this.tileWidth;
        int trueY = round(player.getyPos() / this.tileWidth) * this.tileWidth;
        float prevX = player.getxPos();
        float prevY = player.getyPos();

        player.setxPos(trueX);
        player.setyPos(trueY);
        player.moveBody(prevX, prevY);


        //MOVE PLAYER BASED TO KEY PRESS

        //update the sprites to the next frame
       //before updating the sprites , check for collisions and update the sprites accordingly
        if (lastKeyPressed >= 37 && lastKeyPressed <= 40) {
            player.move(lastKeyPressed);
        }
        if(lastKeyPressed == 87 || lastKeyPressed == 83 || lastKeyPressed == 65 || lastKeyPressed == 68){
            player.move(lastKeyPressed);
        }
        this.collide();
        return sprites;
    }

    private void collide() {
        //check if the player is colliding with a wall
        int x = (int) (player.getxPos() / this.tileWidth);
        int y = (int) (player.getyPos() / this.tileWidth);
        //LEFT
        if (x + player.getDirectionX() < 0) {window.reset();}
        //RIGHT
        if (x + player.getDirectionX() >= cols) {window.reset();}
        //TOP
        if (y + player.getDirectionY() <= 0) {window.reset();}
        //BOTTOM
        if (y + player.getDirectionY() > rows) {window.reset();}
        //check if the player is colliding with a tile
        if (tiles[x+player.getDirectionX()][y+player.getDirectionY()-1] != null) {
            if (tiles[x+player.getDirectionX()][y+player.getDirectionY()-1].isWall()){window.reset();}
            //if (tiles[y+player.getDirectionX()][x+player.getDirectionX()].isFood()){player.grow();}
        }
    }

    private void createMaze() {
        //Generate a maze
        this.tiles = new Tile[rows][cols];
        //generate 20 random walls
        for (int i = 0; i < 20; i++) {
            int x = (int) (Math.random() * cols);
            int y = (int) (Math.random() * rows-1)+1;
            tiles[x][y] = new Tile(x*tileWidth, y*tileWidth, tileWidth, wallImage, true);
        }

        tiles[30][0] = new Tile(30*tileWidth, 0*tileWidth, tileWidth, wallImage, true);
    }
    private void createMaze(File file) {

        this.tiles = new Tile[rows][cols];
        //instantiating the tiles
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                tiles[i][j] = new Tile(j*tileWidth, i*tileWidth, tileWidth, null, false);
            }
        }
        //retrieve the maze from the file
        //todo >>>>>>>>>>>>>>>>>>>>>>>>>>>>@CAMERON<<<<<<<<<<<<<<<<<<<<<<<<<
    }


    public void reset() {
        //System.out.println("x: " + player.getxPos() + " y: " + player.getyPos());
        player.reset();

    }
}
