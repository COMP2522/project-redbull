package org.example;

import processing.core.PApplet;
import processing.event.KeyEvent;

import java.awt.*;
import java.util.ArrayList;

//Window class which functions as the display
public class Window extends PApplet {

    Snake snake;
    Clock clock = new Clock();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    ArrayList<Sprite> sprites = new ArrayList<>();
    SpriteManager spriteManager = new SpriteManager(this);

    private int width = min((int) screenSize.getWidth(), (int) screenSize.getHeight());
    private int height = width;
    private int offset = (int) ((screenSize.getWidth()-width)/(2));
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getOffset() {
        return offset;
    }

    //THESE ARE THE GRID VARIABLES
    int cellSizeX = 10;
    int cellSizeY = 10;
    int rows = height/cellSizeY -1;//-1 to stay square with cols
    int cols = width/cellSizeX -1;//-1 to avoid drawing the below the screen
    //////////////////////////////////////////////////////



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
            sprites = spriteManager.update();
        }

        //color whole screen black
        background(0);
        //this is the play space
        rect(offset,cellSizeY,width-cellSizeY,height-2*cellSizeY);
        drawGrid();
        //draw all sprites
        for (Sprite sprite : sprites) {
            System.out.println(sprite.getxPos());
            sprite.draw();
        }
    }
    public void drawGrid() {
        for (int i = 0; i < rows; i++) {//(screenWidth-gameWidth)/(cellSizeX*2) is to center the grid, it represents the leftmost side of the centered grid
            for (int j = 1; j < cols; j++) {
                stroke(255);
                fill(50,50,50);
                rect((i+(offset/cellSizeX)) * cellSizeX, j * cellSizeY, cellSizeX, cellSizeY);
            }

        }
    }
    //Movement method for the snake
    public void keyPressed(KeyEvent event) {
        // 0 - right
        // 1 - down
        // 2 - left
        // 3 - up

        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case 37:
                // go left
                 snake.setDirection(2);
                break;
            case 39:
                // handle right
                snake.setDirection(0);

                break;

            case 38:
                // handle up
                snake.setDirection(3);
                break;

            case 40:
                // handle down
                snake.setDirection(1);
                break;

        }
    }

    public static void main(String[] args) {
        String[] appletArgs = new String[]{"MazeSnake"};
        Window MazeSnake = new Window();
        PApplet.runSketch(appletArgs, MazeSnake);
    }
}
