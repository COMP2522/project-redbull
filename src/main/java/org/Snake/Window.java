package org.Snake;

import org.Snake.UI.NotInGame.Pages.InGameUI;
import org.Snake.UI.UiManager;
import processing.core.PApplet;
import processing.event.KeyEvent;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;


import org.Snake.Database.MongoDb;

import java.awt.*;
import java.util.Objects;

/**
 * Window class which is the main class for the Snake game.
 *
 * @author
 * @version 1.0
 */
public class Window extends PApplet {

    /**
     * The clock that controls the speed of the game.
     */
    private Clock clock;

    /**
     * The grid that the game is played on.
     */
    private Dimension screenSize;

    /**
     * spriteManager that manages all the sprites in the game
     */
    private SpriteManager spriteManager;


    /**
     * The number of frames per clock tick.
     */
    private int framesPerClock;

    /**
     * The width of the window.
     */
    private final int width;

    /**
     * The offset of the window.
     */
    private final int offset;

    /**
     * The size of each cell in the grid.
     */
    private int cellSize;
    /**
     * The number of rows in the grid.
     */
    private int rows;
    /**
     * The number of columns in the grid.
     */
    private int cols;

    /**
     * The last key that was pressed.
     */
    private int lastKeyPressed;

    /**
     * The top offset of the window.
     */
    private final int topOffSet;
    /**
     * The boolean that determines if the game is active.
     */
    private boolean gameActive;

    /**
     * The database that stores the high scores.
     */
    private final MongoDb mongoDb;
    /**
     * The game frame rate.
     */
    private final int gameFramteRate = 60;


    //////////////////////////////////////////////////////

    /**
     * The constructor for the Window class.
     */
    public Window(){
        gameActive = false;
        //THESE ARE THE GRID VARIABLES
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.width = min((int) (screenSize.getWidth()*0.99), (int) (screenSize.getHeight()*0.99));
        this.offset = (int) ((screenSize.getWidth()-width)/(2));
        this.rows = 37;
        this.cols = 37;
        this.cellSize = width/cols;
        topOffSet = cellSize;

        // place the levelSelector in the center of the screen
//        int centerX = (int) (screenSize.getWidth()/2) - 350;
//        int centerY = (int) (screenSize.getHeight()/2) - 350;

//        levelSelector = new LevelSelector(this, centerX, centerY, 700, 700);
        mongoDb = new MongoDb();
    }

    /**
     * The getter for the width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * The getter for the height offset.
     */
    public int getOffset() {
        return offset;
    }


    @Override
    public void settings() {
        fullScreen();
    }

    /**
     * The method that loads the game
     */
    @Override
    public void setup(){
        this.init();
        this.clock = new Clock();
        this.spriteManager = new SpriteManager(this, this.cellSize, this.rows, this.cols);
        UiManager.getInstance(this, (float)screenSize.getWidth(), (float)screenSize.getHeight(), mongoDb);

        Sprite.loadImages();

//        inGameUI = new InGameUI(this, 0 ,0, (float)screenSize.getWidth(),(float) screenSize.getHeight(), uiManager);

    }

    /**
     * The method that starts the game.
     */

    public void init(){
        frameRate(60);
    }

    /**
     * The method that draws the grid.
     */
    public void draw() {
        if (gameActive) {
            if (clock.tick()) {
                spriteManager.update(lastKeyPressed);
//                System.out.println("sprites size no nulls: " + sprites.size());
            }
            drawGrid();
            spriteManager.animate();
            spriteManager.draw();
            UiManager.getInstance().draw();
//            inGameUI.draw();
        } else {
            background(0);
            UiManager.getInstance().draw();
            if (this.levelSelected() && UiManager.getInstance().getStart()) {
                this.startGame();
                background(0);
            }
        }
    }

    /**
     * The method that draws the grid
     */
    public void drawGrid() {
        for (int i = 0; i < rows-1; i++) {//(screenWidth-gameWidth)/(cellSizeX*2) is to center the grid, it represents the leftmost side of the centered grid
            for (int j = 1; j < cols; j++) {
                if(spriteManager.getTiles()[i][j-1] == null) {
                    stroke(100, 100, 100);
                    fill(225, 237, 238);
                    if ((i % 2 == 0 || j % 2 == 0) && !(i % 2 == 0 && j % 2 == 0)) {
                        //stroke(115,115,115);
                        fill(199, 222, 225);
                    }
                    rect((i * cellSize + offset), j * cellSize , cellSize, cellSize);
                }
            }
        }
    }

    /**
     * The method that handles key presses
     * @param event the key event
     */
    public void keyPressed(KeyEvent event) {
        // 0 - right
        // 1 - down
        // 2 - left
        // 3 - up

        this.lastKeyPressed = event.getKeyCode();

    }

    /**
     * The method that handles mouse clicks
     */
    public void mousePressed() {
        UiManager.getInstance().mouseClicked(this.mouseX, this.mouseY);
    }

    /**
     * The main method that runs the program
     * @param args the arguments
     */
    public static void main(String[] args) {
        String[] appletArgs = new String[]{"MazeSnake"};
        Window MazeSnake = new Window();
        PApplet.runSketch(appletArgs, MazeSnake);
    }

    /**
     * Resets the game upon the snake dying
     */
    public void reset(){
        lastKeyPressed = 0;
        spriteManager.reset();
        clock.reset();

        int score = UiManager.getInstance().getScore();
        if (mongoDb.isHighScore(score, UiManager.getInstance().getSelectedLevel())) {
            String name = JOptionPane.showInputDialog("You got a High Score! Enter your name:");
            if (name != null && !name.isEmpty()) {
                // do something with the user's name
                mongoDb.put(name, score, UiManager.getInstance().getSelectedLevel());
            } else {
                // user canceled the input or didn't enter a name
                mongoDb.put("Anonymous", score, UiManager.getInstance().getSelectedLevel());
            }

            UiManager.getInstance().reDrawHighScores();

        }


        UiManager.getInstance().resetScore();
    }

    /**
     * Cals the methods that make the tiles
     */
    private void startGame() {

        try {
            // load the sound file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream
                    (new File("src/main/SoundTrack/506893__mrthenoronha__upbeat-theme-loop.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // set loop count to infinite
            clip.start();
        } catch (Exception e) {
            JOptionPane.showMessageDialog
                    (null, "Error playing sound: " + e.getMessage());
        }
        spriteManager.setLevel(UiManager.getInstance().getSelectedLevel());
        spriteManager.makeTiles();
        gameActive = true;
    }

    private boolean levelSelected() {
        return !Objects.equals(UiManager.getInstance().getSelectedLevel(), "none");
    }

    /**
     * The getter for the cell size
     */
    public float getTopOffset() {
        return topOffSet;
    }

    /**
     * The method that increments the score upon eating a food
     */
    public void incrementScore() {
        UiManager.getInstance().incrementScore();
    }

    /**
     * The getter for the database
     */
    public MongoDb getDB() {
        return mongoDb;
    }

    /**
     * The setter for the game active
     */
    public void setGameActive(boolean gameActive) {
        this.gameActive = gameActive;
    }

    public boolean isGameActive() {
        return gameActive;
    }

    public float getScreenWidth() {
        return (float) screenSize.getWidth();
    }
}
