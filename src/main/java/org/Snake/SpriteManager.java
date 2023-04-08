package org.Snake;

import java.util.*;

import static java.lang.Math.round;

//Sprite manager method to manage all on-screen sprites

/**
 * LevelSelector class which is the main level selector
 *
 * @author
 * @version 1.0
 */
public class SpriteManager {
    /**
     * The list of sprites
     */
    private final ArrayList<Sprite> sprites;
    /**
     * The player
     */
    private final Snake player;
    /**
     * The width of the tiles
     */
    private final int tileWidth;
    /**
     * The level
     */
    private String level;
    /**
     * The number of rows
     */
    private final int rows;
    /**
     * The number of columns
     */
    private final int cols;
    /**
     * The array of tiles
     */
    private Wall[][] walls;
    /**
     * The array of food
     */
    private Food[][] food;
    /**
     * The array of enemies
     */
    private Enemy[][] enemies;
    /**
     * The window
     */
    private final Window window;

    /**
     * variable to represent the number 0
     */
    private final int zero = 0;
    /**
     * spawn point of the snake
     */
    private int[] spawnPoint = {10,10};
    //Representing processing keycode values with final ints
    public   final int left = 37;
    public final int keyBoardA =65;
    public final int right = 39;
    public final int keyBoardD = 68;
    public   final int up = 38;
    public final int  keyBoardW = 87;
    public final int down = 40;
    public  final int keyBoardS = 83;

    private int directionX = 0;
    private int directionY = 0;

    public SpriteManager(Window window, int cellSize, int rows, int cols) {
        this.window = window;
        this.rows = rows;
        this.cols = cols;
        this.tileWidth = cellSize;
        Sprite.setWindow(window);
        sprites = new ArrayList<>();
        player = Snake.getInstance(spawnPoint[0]*tileWidth, (int) (spawnPoint[1]*tileWidth+ window.getTopOffset()), tileWidth, "headDown");
        sprites.add(player);
        window.fill(zero, zero, zero);
        window.rect(zero, zero, window.getWidth()*3, window.getWidth()*3);
    }
    public Wall[][] getTiles() {
        return walls;
    }

    /**
     * The method that calls mazemaker to place the tiles, spawns and food
     * @return
     */
    public void makeTiles() {
        if (Objects.equals(this.level, "random")) {
            this.walls = MazeMaker3.generateMaze("wall", rows, cols, tileWidth);
            this.spawnPoint = MazeMaker3.generateSpawn(this.walls, rows, cols);
            this.food = MazeMaker3.generateFood(rows, cols, tileWidth, this.walls);
        } else {
            this.walls = MazeMaker.loadMaze("wall", rows, cols, tileWidth, this.level);
            this.spawnPoint = MazeMaker.loadSpawn(this.level);
            this.food = MazeMaker.loadFood(rows, cols, tileWidth, this.level);
            this.enemies = MazeMaker.loadEnemies(rows, cols, tileWidth, this.level);
            for (Wall[] wall : walls) {
                for (Wall wall1 : wall) {
                    sprites.add(wall1);
                    if (wall1 != null) {
                        wall1.draw();
                    }
                }
            }
            try {
                for (Food[] food1 : food) {
                    Collections.addAll(sprites, food1);
                }
            }
            catch (Exception e){
                //System.out.println("No food");
            }
        }

        for (Wall[] wall : walls) {
            Collections.addAll(sprites, wall);
        }
    }


    /**
     * Method to draw the sprites usin threads
     */
    public void draw() {
        for(int i = sprites.size()-1; i >= 0; i--){
            if(sprites.get(i) == null){
                sprites.remove(i);
            }
        }
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
    }

    /**
     * Method to animate the player
     */
    public void animate() {

        //calculate the next position of the player
        float moveX = player.getDirectionX() * (this.tileWidth) / Clock.getFramesPerClock();
        float moveY = player.getDirectionY() * (this.tileWidth) / Clock.getFramesPerClock();
        float nextX = player.getxPos() + moveX;
        float nextY = player.getyPos() + moveY;
        player.setxPos(nextX);
        player.setyPos(nextY);
    }

    /**
     * Method to update all the sprites
     */
    public void update(int lastKeyPressed){

        //if the player direction is 0, then the snake is pointing down, and should not be able to move up
        if (player.getDirectionX() == zero && player.getDirectionY()
                == zero && (lastKeyPressed == up || lastKeyPressed == keyBoardW)) {
//            return sprites;
            return;
        }


        //update the sprites to the next frame
        int trueX = round(player.getxPos() / this.tileWidth) * this.tileWidth;
        int trueY = round(player.getyPos() / this.tileWidth) * this.tileWidth;
        player.setxPos(trueX);
        player.setyPos(trueY);
        player.moveBody(player.getxPos(), player.getyPos());


        //MOVE PLAYER BASED TO KEY PRESS

        //update the sprites to the next frame
       //before updating the sprites , check for collisions and update the sprites accordingly


        if (lastKeyPressed >= left && lastKeyPressed <= down) {
            player.move(lastKeyPressed);
        }
        if(lastKeyPressed == keyBoardW || lastKeyPressed == keyBoardS
                || lastKeyPressed == keyBoardA || lastKeyPressed == keyBoardD){
            player.move(lastKeyPressed);
        }
        this.collide();
    }

    /**
     * Method to check if the player is colliding with a wall or a tile
     */
    private void collide() {

        //check if the player is colliding with a wall
        int x = (int) (player.getxPos() / this.tileWidth);
        int y = (int) (player.getyPos() / this.tileWidth);
        //LEFT
        if (x + player.getDirectionX() < zero) {window.reset();}
        //RIGHT
        if (x + player.getDirectionX() >= cols) {window.reset();}
        //TOP
        if (y + player.getDirectionY() <= zero) {window.reset();}
        //BOTTOM
        if (y + player.getDirectionY() > rows) {window.reset();}
        //check if the player is colliding with a tile
        if (walls[x+player.getDirectionX()][y+player.getDirectionY()-1] != null) {
            if (walls[x+player.getDirectionX()][y+player.getDirectionY()-1].isWall()){window.reset();}
        }
        // collision with food
        if (food[x+player.getDirectionX()][y+player.getDirectionY()-1] != null
                && !food[x+player.getDirectionX()][y+player.getDirectionY()-1].isEaten()) {
            player.grow();
            window.incrementScore();
            if (this.level.equals("Classic") || this.level.equals("Random")){
                Random rand = new Random();
                int newX = rand.nextInt(rows-3)+1;
                int newY = rand.nextInt(cols-3)+1;
                food[x+player.getDirectionX()][y+player.getDirectionY()-1].eat();
                food[newX][newY] = new Food(newX*tileWidth,
                        newY*tileWidth, tileWidth, "food");

                sprites.remove(food[x+player.getDirectionX()][y+player.getDirectionY()-1]);
                food[x+player.getDirectionX()][y+player.getDirectionY()-1] = null;
                sprites.add(food[newX][newY]);
            }
            else if(this.level.equals("Modern")) {
                Random rand = new Random();
                int newX = rand.nextInt(rows-3)+1;
                int newY = rand.nextInt(cols-3)+1;
                while (walls[newX][newY] != null ||
                        (player.getxPos() == newX*tileWidth && player.getyPos() == newY*tileWidth)||
                        (player.getxPos()+ player.getDirectionX()*tileWidth
                                == newX*tileWidth && player.getyPos()+ player.getDirectionY()*tileWidth == newY*tileWidth)){

                    newX = rand.nextInt(rows-3)+1;
                    newY = rand.nextInt(cols-3)+1;
                }
                walls[newX][newY] = new Wall(newX*tileWidth, newY*tileWidth, tileWidth, "wall", true);
                walls[newX][newY].draw();
                sprites.add(walls[newX][newY]);

                newX = rand.nextInt(rows-3)+1;
                newY = rand.nextInt(cols-3)+1;
                while (walls[newX][newY] != null){
                    newX = rand.nextInt(rows-3)+1;
                    newY = rand.nextInt(cols-3)+1;
                }
                food[x+player.getDirectionX()][y+player.getDirectionY()-1].eat();
                sprites.remove(food[x+player.getDirectionX()][y+player.getDirectionY()-1]);
                food[newX][newY] = new Food(newX*tileWidth, newY*tileWidth, tileWidth, "food");
                food[x+player.getDirectionX()][y+player.getDirectionY()-1] = null;
                sprites.add(food[newX][newY]);
            }else {
                food[x + player.getDirectionX()][y + player.getDirectionY() - 1].eat();
            }
        }
        for (int i = 0; i < player.getBody().size()-1; i++) {
            if (player.getBody().get(i).getxPos() == player.getxPos() && player.getBody().get(i).getyPos() == player.getyPos()) {
                window.reset();
            }
        }


    }

    /**
     * Method to reset the topography of the level upon the player's death
     */
    public void reset() {
        sprites.clear();
        makeTiles();
        player.reset(spawnPoint[0], spawnPoint[1]);
        sprites.add(player);
        for (Food[] food1 : food) {
            for (Food food2 : food1) {
                try{
                    food2.reset();
                }
                catch (Exception e){
                }
            }
        }
    }

    /**
     * Method to get the level to be loaded
     * @param level the level to be loaded
     */
    public void setLevel(String level) {
        this.level = level;
    }
}
