package org.Snake;

import org.Snake.UI.InGame.InGameUI;
import org.Snake.UI.NotInGame.NotInGameUiManager;
import processing.core.PApplet;
import processing.event.KeyEvent;

import org.Snake.Database.MongoDb;

import java.awt.*;
import java.util.Objects;
import javax.swing.JOptionPane;
/**
 * Window class which is the main class for the Snake game
 *
 * @author
 * @version
 */
public class Window extends PApplet {

    /**
     * The clock that controls the speed of the game
     */
    Clock clock;

    /**
     * The size of the screen
     */
    Dimension screenSize;

    /**
     * The sprite manager that manages the sprites
     */
    SpriteManager spriteManager;

    /**
     * The not in game ui manager that manages the ui in the menu pages
     */
    NotInGameUiManager notInGameUiManager;


    int framesPerClock;

    /**
     * The width of the window
     */
    private final int width;

    /**
     * The offset of the window
     */
    private final int offset;

    /**
     * The size of the cells for the individual window
     */
    int cellSize;

    /**
     * The number of rows in the grid
     */
    int rows;

    /**
     * The number of columns in the grid
     */
    int cols;

    /**
     * The in game ui that manages the ui in the game
     */
    InGameUI inGameUI;

    /**
     * The last key pressed
     */
    int lastKeyPressed;

    /**
     * The top offset of the window
     */
    private final int topOffSet;

    /**
     * The boolean that determines if the game is active
     */
    boolean gameActive;

    /**
     * The database that stores the high scores
     */
    MongoDb mongoDb;


    //////////////////////////////////////////////////////

    /**
     * The constructor for the Window class
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
        this.spriteManager = new SpriteManager(this, this.cellSize, this.rows, this.cols);
        this.notInGameUiManager = new NotInGameUiManager(this, 0,0, (float)screenSize.getWidth(), (float)screenSize.getHeight(), mongoDb, this);
        Sprite.loadImages();
        inGameUI = new InGameUI(this, 0 ,0, (float)screenSize.getWidth(),(float) screenSize.getHeight(), notInGameUiManager);

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
        if (gameActive) {
            if (clock.tick()) {
                spriteManager.update(lastKeyPressed);
//                System.out.println("sprites size no nulls: " + sprites.size());
            }
            drawGrid();
            spriteManager.animate();
            spriteManager.draw();
            inGameUI.draw();
        } else {
            background(0);
            this.notInGameUiManager.draw();
            if (this.levelSelected() && notInGameUiManager.getStart()) {
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
        if (!gameActive) {
            notInGameUiManager.mouseClicked(this.mouseX, this.mouseY);
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
        lastKeyPressed = 0;
        spriteManager.reset();
        clock.reset();

        int score = inGameUI.getScore();
        if (mongoDb.isHighScore(score, notInGameUiManager.getSelectedLevel())) {
            String name = JOptionPane.showInputDialog("You got a High Score! Enter your name:");
            if (name != null && !name.isEmpty()) {
                // do something with the user's name
                mongoDb.put(name, score, notInGameUiManager.getSelectedLevel());
            } else {
                // user canceled the input or didn't enter a name
                mongoDb.put("Anonymous", score, notInGameUiManager.getSelectedLevel());
            }
        }


        inGameUI.resetScore();
    }

    /**
     * Cals the methods that make the tiles
     */
    private void startGame() {
        spriteManager.setLevel(notInGameUiManager.getSelectedLevel());
        spriteManager.makeTiles();
        gameActive = true;
    }

    /**
     * Checks if a level is selected
     * @return true if a level is selected
     */
    private boolean levelSelected() {
        return !Objects.equals(notInGameUiManager.getSelectedLevel(), "none");
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
        inGameUI.incrementScore();
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
}