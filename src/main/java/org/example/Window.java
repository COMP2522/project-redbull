package org.example;

import processing.core.PApplet;

import java.awt.*;
import java.util.ArrayList;

public class Window extends PApplet{
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    ArrayList<Sprite> sprites = new ArrayList<>();
    SpriteManager spriteManager = new SpriteManager(sprites);
    int screenWidth = (int) screenSize.getWidth();
    int screenHeight = (int) screenSize.getHeight();
    int width = min(screenWidth,screenHeight);
    int height = width;
    int cellSizeX = 10;
    int cellSizeY = 10;
    int rows = height/cellSizeY -1;//-1 to stay square with cols
    int cols = width/cellSizeX -1;//-1 to avoid drawing the below the screen

    @Override
    public void settings() {
        size(screenWidth, screenHeight);

    }
    @Override
    public void setup(){
        background(0);
    }

    public void draw() {
        drawGrid();
    }
    public void drawGrid() {
        for (int i = (screenWidth-width)/(cellSizeX*2); i < rows+(screenWidth-width)/(cellSizeX*2); i++) {//(screenWidth-gameWidth)/(cellSizeX*2) is to center the grid, it represents the leftmost side of the centered grid
            for (int j = 0; j < cols; j++) {
                stroke(255);
                noFill();
                rect(i * cellSizeX, j * cellSizeY, cellSizeX, cellSizeY);
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
