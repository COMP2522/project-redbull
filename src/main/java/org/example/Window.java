package org.example;

import processing.core.PApplet;
import processing.event.KeyEvent;

import java.awt.*;
import java.util.ArrayList;

public class Window extends PApplet {

    Snake snake;
    Clock clock;
    Dimension screenSize;
    ArrayList<Sprite> sprites;
    SpriteManager spriteManager;

    private int width;
    private int height;
    private int offset;

    //THESE ARE THE GRID VARIABLES
    // these are place holders?
    int cellSize;
    int rows;
    int cols;

    int lastKeyPressed;
    //////////////////////////////////////////////////////

    public Window(){
        //THESE ARE THE GRID VARIABLES
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.width = min((int) screenSize.getWidth(), (int) screenSize.getHeight());
        this.height = width;
        this.offset = (int) ((screenSize.getWidth()-width)/(2));
        this.rows = 36;
        this.cols = 36;
        this.cellSize = width/cols;
        // ////////////////////////////////////////////////////

        this.clock = new Clock();
        this.sprites = new ArrayList<>();
        this.spriteManager = new SpriteManager(this, this.cellSize, this.rows, this.cols);
        sprites = spriteManager.update(39);


    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getOffset() {
        return offset;
    }

    @Override
    public void settings() {
        fullScreen();
    }
    @Override
    public void setup(){
        this.init();
    }
    public void init(){
        background(0);
        frameRate(60);
        fill(255);
    }
    public void draw() {
        if (clock.tick()){
            sprites = spriteManager.update(lastKeyPressed);
        }

        //color whole screen black
        background(0);
        //this is the play space
        rect(offset,cellSize,width-cellSize,height-2*cellSize);
//        drawGrid();
        //draw all sprites
        for (Sprite sprite : sprites) {
            //System.out.println(sprite.getxPos());
            if (sprite != null){
                sprite.draw();
            }
        }
    }
    public void drawGrid() {
        for (int i = 0; i < rows; i++) {//(screenWidth-gameWidth)/(cellSizeX*2) is to center the grid, it represents the leftmost side of the centered grid
            for (int j = 1; j < cols; j++) {
                stroke(255);
                fill(50,50,50);
                rect((i+(offset/cellSize)) * cellSize, j * cellSize, cellSize, cellSize);
            }

        }
    }
    public void keyPressed(KeyEvent event) {
        // 0 - right
        // 1 - down
        // 2 - left
        // 3 - up

        this.lastKeyPressed = event.getKeyCode();

    }

    public static void main(String[] args) {
        String[] appletArgs = new String[]{"MazeSnake"};
        Window MazeSnake = new Window();
        PApplet.runSketch(appletArgs, MazeSnake);
    }
}