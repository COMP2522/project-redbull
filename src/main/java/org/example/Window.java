package org.example;

import processing.core.PApplet;

import java.awt.*;
import java.util.ArrayList;

public class Window extends PApplet {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    ArrayList<Sprite> sprites = new ArrayList<>();
    SpriteManager spriteManager = new SpriteManager(this);
    int screenWidth = (int) screenSize.getWidth();
    int screenHeight = (int) screenSize.getHeight();
    int width = min(screenWidth,screenHeight);
    int height = width;
    int cellSizeX = 10;
    int cellSizeY = 10;
    int rows = height/cellSizeY -1;//-1 to stay square with cols
    int cols = width/cellSizeX -1;//-1 to avoid drawing the below the screen
    int offset = (screenWidth-width)/(2);


    @Override
    public void settings() {
        size(screenWidth, screenHeight);
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
        sprites = spriteManager.update();
        //color whole screen black
        background(0);
        //this is the play space
        rect(offset,cellSizeY,width-cellSizeY,height-2*cellSizeY);
//        drawGrid();
        //draw all sprites
        for (Sprite sprite : sprites) {
            sprite.draw();
        }
    }
    public void drawGrid() {
        for (int i = 0; i < rows; i++) {//(screenWidth-gameWidth)/(cellSizeX*2) is to center the grid, it represents the leftmost side of the centered grid
            for (int j = 1; j < cols; j++) {
                stroke(255);
                fill(50,50,50);
                rect((i+offset/cellSizeX) * cellSizeX, j * cellSizeY, cellSizeX, cellSizeY);
            }

        }
    }
    public static void main(String[] args) {
        String[] appletArgs = new String[]{"MazeSnake"};
        Window MazeSnake = new Window();
        System.out.println(MazeSnake.displayHeight);
        PApplet.runSketch(appletArgs, MazeSnake);
    }
}
