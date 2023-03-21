package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import static java.lang.Math.round;

//Sprite manager method to manage all on screen sprites
public class SpriteManager {
    private ArrayList<Sprite> sprites;


    private Snake player;
    private SnakeBody body1;
    private SnakeBody body2;
    private SnakeBody body3;
    private SnakeBody body4;
    private SnakeBody body5;
    private SnakeBody body6;
    private SnakeBody tail;
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
        this.tiles = MazeMaker3.generateMaze(wallImage, rows, cols, cellsize, 0);
        sprites = new ArrayList<>();

        player = Snake.getInstance(5*tileWidth, (int) (10*tileWidth+ window.getTopOffset()), tileWidth, snakeImage);
        body1 =  new SnakeBody(5*tileWidth, (int) (9*tileWidth+ window.getTopOffset()), tileWidth, bodyImage);
        body2 =  new SnakeBody(5*tileWidth, (int) (8*tileWidth+ window.getTopOffset()), tileWidth, bodyImage);
        body3 =  new SnakeBody(5*tileWidth, (int) (7*tileWidth+ window.getTopOffset()), tileWidth, bodyImage);
        body4 =  new SnakeBody(5*tileWidth, (int) (6*tileWidth+ window.getTopOffset()), tileWidth, bodyImage);
        body5 =  new SnakeBody(5*tileWidth, (int) (5*tileWidth+ window.getTopOffset()), tileWidth, bodyImage);
        body6 =  new SnakeBody(5*tileWidth, (int) (4*tileWidth+ window.getTopOffset()), tileWidth, bodyImage);
        tail =  new SnakeBody(5*tileWidth, (int) (3*tileWidth+ window.getTopOffset()), tileWidth, tailImage);
        sprites.add(player);

        sprites.add(body1);
        sprites.add(body2);
        sprites.add(body3);
        sprites.add(body4);
        sprites.add(body5);
        sprites.add(body6);
        sprites.add(tail);

        player.grow(body1);
        player.grow(body2);
        player.grow(body3);
        player.grow(body4);
        player.grow(body5);
        player.grow(body6);
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

        //if the player direction is 0, then the snake is pointing down, and should not be able to move up
        if (player.getDirectionX() == 0 && player.getDirectionY() == 0 && (lastKeyPressed == 38 || lastKeyPressed == 87)) {
            return sprites;
        }


        //update the sprites to the next frame
        int trueX = round(player.getxPos() / this.tileWidth) * this.tileWidth;
        int trueY = round(player.getyPos() / this.tileWidth) * this.tileWidth;
        float prevX = player.getxPos();
        float prevY = player.getyPos();

        player.setxPos(trueX);
        player.setyPos(trueY);


        //MOVE PLAYER BASED TO KEY PRESS

        //update the sprites to the next frame
       //before updating the sprites , check for collisions and update the sprites accordingly
        if (lastKeyPressed >= 37 && lastKeyPressed <= 40) {
            player.move(lastKeyPressed);
        }
        if(lastKeyPressed == 87 || lastKeyPressed == 83 || lastKeyPressed == 65 || lastKeyPressed == 68){
            player.move(lastKeyPressed);
        }
        if (lastKeyPressed !=0 ) {
            player.moveBody(prevX, prevY);
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

    private void createMaze(){
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

    public void reset() {
        //System.out.println("x: " + player.getxPos() + " y: " + player.getyPos());
        player.reset(body1, body2, tail);
        sprites.remove(body3);
        sprites.remove(body4);
        sprites.remove(body5);
        sprites.remove(body6);
    }
}
