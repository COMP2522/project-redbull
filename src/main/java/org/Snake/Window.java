package org.Snake;

import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private String wallImage = "src" + File.separator + "main" + File.separator + "images" + File.separator + "wall.png";

    //THESE ARE THE GRID VARIABLES
    // these are place holders?
    int cellSize;
    int rows;
    int cols;

    LevelSelector ui;

    InGameUI inGameUI;

    int lastKeyPressed;
    private int topOffSet;

    boolean gameActive;


    //////////////////////////////////////////////////////

    public Window(){
        gameActive = false;
        //THESE ARE THE GRID VARIABLES
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.width = min((int) (screenSize.getWidth()*0.99), (int) (screenSize.getHeight()*0.99));
        this.height = width;
        this.offset = (int) ((screenSize.getWidth()-width)/(2));
        this.rows = 37;
        this.cols = 37;
        this.cellSize = width/cols;
        topOffSet = cellSize;
//        System.out.println("cellsize: " + cellSize);
//        System.out.println("rows: " + rows + " cols: " + cols);
        // ////////////////////////////////////////////////////
//        sprites = spriteManager.update(lastKeyPressed);
//        sprites = spriteManager.animate(60);

        // place the ui in the center of the screen
        int centerX = (int) (screenSize.getWidth()/2) - 350;
        int centerY = (int) (screenSize.getHeight()/2) - 350;

        ui = new LevelSelector(this, centerX, centerY, 700, 700);
        inGameUI = new InGameUI(this, 0 ,0, (float)screenSize.getWidth(),(float) screenSize.getHeight());

    }

    public int getLastKeyPressed() {
        return lastKeyPressed;
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
        Sprite.loadImages();

    }
    public void init(){
        frameRate(60);
    }
    public void draw() {
        if (gameActive) {
            if (clock.tick()) {
                sprites = spriteManager.update(lastKeyPressed);
//                System.out.println("sprites size: " + sprites.size());
                for(int i = sprites.size()-1; i >= 0; i--){
                    if(sprites.get(i) == null){
                        sprites.remove(i);
                    }
                }
//                System.out.println("sprites size no nulls: " + sprites.size());
            }
            sprites = spriteManager.animate();
            drawGrid();
            pushStyle();
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

            popStyle();


            // draw the UIComponent

            inGameUI.draw();

//        textAlign(RIGHT, TOP);
//        text(String.format("FPC: %.0f", Clock.getFramesPerClock()), width, 0);
//        text(String.format("FPS: %.0f", Clock.getFramesPerSecond()), width, +10);
        } else {
            PImage background_image = loadImage("src/main/images/grassBackground.png");

            image(background_image, 0, 0, (float)screenSize.getWidth(), (float)screenSize.getHeight());
            ui.draw();
            if (!Objects.equals(ui.getSelectedLevel(), "none")) {
                spriteManager.setLevel(ui.getSelectedLevel());
                spriteManager.makeTiles();
                gameActive = true;
//                reset();
            }
        }
    }
    public void drawGrid() {
        for (int i = 0; i < rows-1; i++) {//(screenWidth-gameWidth)/(cellSizeX*2) is to center the grid, it represents the leftmost side of the centered grid
            for (int j = 1; j < cols; j++) {
                if(spriteManager.getTiles()[i][j-1] == null) {
                    stroke(100, 100, 100);
                    fill(100, 100, 100);
                    if ((i % 2 == 0 || j % 2 == 0) && !(i % 2 == 0 && j % 2 == 0)) {
                        //stroke(115,115,115);
                        fill(115, 115, 115);
                    }
                    rect((i * cellSize + offset), j * cellSize , cellSize, cellSize);
                }
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

    public void mousePressed() {
        ui.mouseClicked(this.mouseX, this.mouseY);
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
        inGameUI.resetScore();
    }

    public float getTopOffset() {
        return topOffSet;
    }

    public void incrementScore() {
        inGameUI.incrementScore();
    }
    public void resetScore() {
        inGameUI.resetScore();
    }
}