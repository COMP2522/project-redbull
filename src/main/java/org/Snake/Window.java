package org.Snake;

import org.Snake.UI.InGame.InGameUI;
import org.Snake.UI.NotInGame.NotInGameUiManager;
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

    private Dimension screenSize;

    private SpriteManager spriteManager;

    private NotInGameUiManager notInGameUiManager;


    private int framesPerClock;

    /**
     * The width of the window
     */
    private final int width;

    /**
     * The offset of the window
     */
    private final int offset;

    private int cellSize;

    private int rows;

    private int cols;

    private InGameUI inGameUI;

    private int lastKeyPressed;

    /**
     * The top offset of the window
     */
    private final int topOffSet;

    private boolean gameActive;

    /**
     * The database that stores the high scores
     */
    MongoDb mongoDb;


    //////////////////////////////////////////////////////

    /**
     * The constructor for the Window class
     */
    private final int numRows = 37;
    private final int numCols = 37;
    public Window(){
        setGameActive(false);
        //THESE ARE THE GRID VARIABLES
        this.setScreenSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.width = min((int) (getScreenSize().getWidth()*0.99), (int) (getScreenSize().getHeight()*0.99));
        this.offset = (int) ((getScreenSize().getWidth()-width)/(2));
        this.setRows(numRows);
        this.setCols(numCols);
        this.setCellSize(width/ getCols());
        topOffSet = getCellSize();

        // place the levelSelector in the center of the screen
//        int centerX = (int) (screenSize.getWidth()/2) - 350;
//        int centerY = (int) (screenSize.getHeight()/2) - 350;

//        levelSelector = new LevelSelector(this, centerX, centerY, 700, 700);
        mongoDb = new MongoDb();
    }

    /**
     * The getter for the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * The getter for the height offset
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
        this.setSpriteManager(new SpriteManager(this, this.getCellSize(), this.getRows(), this.getCols()));
        this.setNotInGameUiManager(new NotInGameUiManager(this, 0,0, (float) getScreenSize().getWidth(), (float) getScreenSize().getHeight(), mongoDb, this));
        Sprite.loadImages();
        setInGameUI(new InGameUI(this, 0 ,0, (float) getScreenSize().getWidth(),(float) getScreenSize().getHeight(), getNotInGameUiManager()));

    }

    /**
     * The method that starts the game
     */
    public void init(){
        frameRate(60);
    }

    /**
     * The method that draws the grid
     */
    public void draw() {
        if (isGameActive()) {
            if (clock.tick()) {
                getSpriteManager().update(getLastKeyPressed());
//                System.out.println("sprites size no nulls: " + sprites.size());
            }
            drawGrid();
            getSpriteManager().animate();
            getSpriteManager().draw();
            getInGameUI().draw();
        } else {
            background(0);
            this.getNotInGameUiManager().draw();
            if (this.levelSelected() && getNotInGameUiManager().getStart()) {
                this.startGame();
                background(0);
            }
        }
    }

    /**
     * The method that draws the grid
     */
    public void drawGrid() {
        for (int i = 0; i < getRows() -1; i++) {//(screenWidth-gameWidth)/(cellSizeX*2) is to center the grid, it represents the leftmost side of the centered grid
            for (int j = 1; j < getCols(); j++) {
                if(getSpriteManager().getTiles()[i][j-1] == null) {
                    stroke(100, 100, 100);
                    fill(225, 237, 238);
                    if ((i % 2 == 0 || j % 2 == 0) && !(i % 2 == 0 && j % 2 == 0)) {
                        //stroke(115,115,115);
                        fill(199, 222, 225);
                    }
                    rect((i * getCellSize() + offset), j * getCellSize(), getCellSize(), getCellSize());
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

        this.setLastKeyPressed(event.getKeyCode());

    }

    /**
     * The method that handles mouse clicks
     */
    public void mousePressed() {
        if (!isGameActive()) {
            getNotInGameUiManager().mouseClicked(this.mouseX, this.mouseY);
        }
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
        setLastKeyPressed(0);
        getSpriteManager().reset();
        clock.reset();

        int score = getInGameUI().getScore();
        if (mongoDb.isHighScore(score, getNotInGameUiManager().getSelectedLevel())) {
            String name = JOptionPane.showInputDialog("You got a High Score! Enter your name:");
            if (name != null && !name.isEmpty()) {
                // do something with the user's name
                mongoDb.put(name, score, getNotInGameUiManager().getSelectedLevel());
            } else {
                // user canceled the input or didn't enter a name
                mongoDb.put("Anonymous", score, getNotInGameUiManager().getSelectedLevel());
            }
        }


        getInGameUI().resetScore();
    }

    /**
     * Cals the methods that make the tiles
     */
    private void startGame() {

        try {
            // load the sound file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/main/SoundTrack/506893__mrthenoronha__upbeat-theme-loop.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // set loop count to infinite
            clip.start();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error playing sound: " + e.getMessage());
        }

        getSpriteManager().setLevel(getNotInGameUiManager().getSelectedLevel());
        getSpriteManager().makeTiles();
        setGameActive(true);
    }

    /**
     * Checks if a level is selected
     * @return true if a level is selected
     */
    private boolean levelSelected() {
        return !Objects.equals(getNotInGameUiManager().getSelectedLevel(), "none");
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
        getInGameUI().incrementScore();
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

    /**
     * The size of the screen.
     */
    public Dimension getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(Dimension screenSize) {
        this.screenSize = screenSize;
    }

    /**
     * The sprite manager that manages the sprites.
     */
    public SpriteManager getSpriteManager() {
        return spriteManager;
    }

    public void setSpriteManager(SpriteManager spriteManager) {
        this.spriteManager = spriteManager;
    }

    /**
     * The not in game ui manager that manages the ui in the menu pages
     */
    public NotInGameUiManager getNotInGameUiManager() {
        return notInGameUiManager;
    }

    public void setNotInGameUiManager(NotInGameUiManager notInGameUiManager) {
        this.notInGameUiManager = notInGameUiManager;
    }

    public int getFramesPerClock() {
        return framesPerClock;
    }

    public void setFramesPerClock(int framesPerClock) {
        this.framesPerClock = framesPerClock;
    }

    /**
     * The size of the cells for the individual window
     */
    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    /**
     * The number of rows in the grid
     */
    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * The number of columns in the grid
     */
    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    /**
     * The in game ui that manages the ui in the game
     */
    public InGameUI getInGameUI() {
        return inGameUI;
    }

    public void setInGameUI(InGameUI inGameUI) {
        this.inGameUI = inGameUI;
    }

    /**
     * The last key pressed
     */
    public int getLastKeyPressed() {
        return lastKeyPressed;
    }

    public void setLastKeyPressed(int lastKeyPressed) {
        this.lastKeyPressed = lastKeyPressed;
    }

    /**
     * The boolean that determines if the game is active
     */
    public boolean isGameActive() {
        return gameActive;
    }
}
