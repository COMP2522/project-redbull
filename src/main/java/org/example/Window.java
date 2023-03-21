package org.example;

import processing.core.PApplet;
import processing.event.KeyEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Window extends PApplet {

    Snake snake;
    Clock clock;
    Dimension screenSize;
    ArrayList<Sprite> sprites;
    SpriteManager spriteManager;

    private int width;
    private int height;
    private int offset;
    int framesPerClock;

    //THESE ARE THE GRID VARIABLES
    // these are place holders?
    int cellSize;
    int rows;
    int cols;

    int lastKeyPressed;
    private int topOffSet;
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
        topOffSet = cellSize;
//        System.out.println("cellsize: " + cellSize);
//        System.out.println("rows: " + rows + " cols: " + cols);
        // ////////////////////////////////////////////////////
//        sprites = spriteManager.update(39);
//        sprites = spriteManager.animate(60);


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
        this.clock = new Clock();
        this.sprites = new ArrayList<>();
        this.spriteManager = new SpriteManager(this, this.cellSize, this.rows, this.cols);


    }
    public void init(){
        frameRate(60);
    }
    public void draw() {
        if (clock.tick()){
            sprites = spriteManager.update(lastKeyPressed);
        }
        sprites = spriteManager.animate();
        //color whole screen black
        background(0);
        //this is the play space, color it white
        fill(255,255,255);
        rect(offset,cellSize,cols*cellSize,rows*cellSize);
//        drawGrid();
        //draw all sprites
//        for (Sprite sprite : sprites) {
//            //System.out.println(sprite.getxPos());
//            if (sprite != null){
//                sprite.draw();
//            }
//        }
        //threaded sprite draw
        int numThreads = 4; // The number of threads to use
        int chunkSize = (int) Math.ceil((double) sprites.size() / numThreads); // The size of each chunk

        List<Thread> threads = new ArrayList<>(); // A list to hold the threads

        for (int i = 0; i < numThreads; i++) {
            final int start = i * chunkSize;
            final int end = Math.min(start + chunkSize, sprites.size());

            // Create a new thread to process the current chunk
            Thread thread = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    Sprite sprite = sprites.get(j);
                    if (sprite != null) {
                        sprite.draw();
                    }
                }
            });

            threads.add(thread);
            thread.start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        textAlign(RIGHT, TOP);
//        text(String.format("FPC: %.0f", Clock.getFramesPerClock()), width, 0);
//        text(String.format("FPS: %.0f", Clock.getFramesPerSecond()), width, +10);
    }
    public void drawGrid() {
        for (int i = 0; i < rows-1; i++) {//(screenWidth-gameWidth)/(cellSizeX*2) is to center the grid, it represents the leftmost side of the centered grid
            for (int j = 1; j < cols-1; j++) {
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

    public void reset(){
        lastKeyPressed = 0;
        spriteManager.reset();
        clock.reset();
    }

    public float getTopOffset() {
        return topOffSet;
    }
}