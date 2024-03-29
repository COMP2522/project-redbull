package org.Snake;

import org.Snake.Enemies.Beetle;
import org.Snake.Enemies.BeetleQueue;
import org.Snake.Enemies.Void;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import static java.lang.Math.round;

/**
 * The main manager for all  the sprites in the game.
 *
 */
public class SpriteManager {
    /**
     * The list of sprites
     */
    private final CopyOnWriteArrayList<Sprite> sprites;
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
     * The array of beetle spawn points
     */
    private Enemy[][] beetleSpawn;

    /**
     * The array of void spawn points
     */
    private Enemy [][] voidSpawn;

    /**
     * The array of voids
     */
    private BeetleQueue beetles;

    /**
     * The array of voids
     */
    private ArrayList <Void> voids;

    /**
     * The minimum allowed number of beetles on the screen
     */
    private static final int MIN_BEETLES = 6;

    /**
     * The number of beetles to add when the minimum is reached
     */
    private static final int ADDITIONAL_BEETLES = 3;

    /**
     * The array of voids
     */
    private boolean playerMoved = false;

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

    /**
     * The counter for how many moves the beetles have made
     */
    private int beetleMoveCounter;

    /**
     * The frequency of beetle moves
     */
    private static final int BEETLE_MOVE_FREQUENCY = 2;


    //Representing processing keycode values with final ints
    public   final int left = 37;
    public final int keyBoardA =65;
    public final int keyBoardD = 68;
    public   final int up = 38;
    public final int  keyBoardW = 87;
    public final int down = 40;
    public  final int keyBoardS = 83;

    /**
     * The constructor for the sprite manager
     * @param window the window
     * @param cellSize the size of the tiles
     * @param rows the number of rows
     * @param cols the number of columns
     */
    public SpriteManager(Window window, int cellSize, int rows, int cols) {
        this.window = window;
        this.rows = rows;
        this.cols = cols;
        this.tileWidth = cellSize;
        Sprite.setWindow(window);
        sprites = new CopyOnWriteArrayList<>();
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
     */
    public void makeTiles() {
        if (Objects.equals(this.level, "random")) {
            this.walls = MazeMaker3.generateMaze("wall", rows, cols, tileWidth);
            this.spawnPoint = MazeMaker3.generateSpawn(this.walls);
        } else {
            this.walls = MazeMaker.loadMaze("wall", rows, cols, tileWidth, this.level);
            this.spawnPoint = MazeMaker.loadSpawn(this.level);
            this.food = MazeMaker.loadFood(rows, cols, tileWidth, this.level);
            this.beetleSpawn = MazeMaker.loadBeetleSpawn(rows, cols, tileWidth, this.level);
            this.voidSpawn = MazeMaker.loadVoidSpawn(rows, cols, tileWidth, this.level);
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
            generateBeetles();
            generateVoids();
        }
        for (Wall[] wall : walls) {
            Collections.addAll(sprites, wall);
        }
    }

    /**
     * The method that creates the beetles on the screen
     */
    public void generateBeetles() {
        beetles = new BeetleQueue();
        for (Enemy[] enemy : beetleSpawn) {
            for (Enemy enemy1 : enemy) {
                if (enemy1 != null) {
                    Beetle beetle = new Beetle((int) enemy1.getxPos(), (int) enemy1.getyPos(), tileWidth, "beetle");
                    sprites.add(beetle);
                    beetles.add(beetle);
                }
            }
        }
    }

    /**
     * Method to generate a void for each void spawn and add the void to the sprite list
     */
    public void generateVoids() {
        voids = new ArrayList<>();
        for (Enemy[] enemy : voidSpawn) {
            for (Enemy enemy1 : enemy) {
                if (enemy1 != null) {
                    Void void1 = new Void((int) enemy1.getxPos(), (int) enemy1.getyPos(), tileWidth, "void");
                    sprites.add(void1);
                    voids.add(void1);

                }
            }
        }
    }


    /**
     * Method to draw the sprites using threads
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
        beetleMoveCounter++;
        //if the player direction is 0, then the snake is pointing down, and should not be able to move up
        if (player.getDirectionX() == zero && player.getDirectionY()
                == zero && (lastKeyPressed == up || lastKeyPressed == keyBoardW)) {
            return;
        }

        if (playerMoved && beetleMoveCounter % BEETLE_MOVE_FREQUENCY == 0) {
            for (Beetle beetle : beetles) {
                beetle.move();
                // check if the player is colliding with a wall
                int x = (int) (beetle.getxPos() / this.tileWidth);
                int y = (int) (beetle.getyPos() / this.tileWidth);
                // LEFT
                if (x < zero || x >= cols || y < zero || y >= rows) {
                    beetle.setInbounds(false);
                }

                // check if the beetle is colliding with a tile, if so, remove it from the lists
                if (walls[x][y] != null) {
                    if (walls[x][y].isWall()) {
                        sprites.remove(beetle);
                        beetles.remove(beetle);
                    }
                }
            }
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
            playerMoved = true;
        }
        if(lastKeyPressed == keyBoardW || lastKeyPressed == keyBoardS
                || lastKeyPressed == keyBoardA || lastKeyPressed == keyBoardD){
            player.move(lastKeyPressed);
            playerMoved = true;
        }
        this.collide();

        //spawn additional beetles if needed
        spawnAdditionalBeetles();
    }

    /**
     * Method to check if the player is colliding with a wall or a tile or a beetle
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
            if (this.level.equals("Classic")){
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
        //check if the player is colliding with itself
        for (int i = 0; i < player.getBody().size()-1; i++) {
            if (player.getBody().get(i).getxPos() == player.getxPos() && player.getBody().get(i).getyPos() == player.getyPos()) {
                window.reset();
            }
            for (Beetle beetle : beetles) {
                if (player.getBody().get(i).getxPos() == beetle.getxPos() && player.getBody().get(i).getyPos() == beetle.getyPos()) {
                    window.reset();
                }
            }
        }

        //check if the player is colliding with a beetle or void
        for (Beetle beetle : beetles) {
            if (beetle.getxPos() == player.getxPos() && beetle.getyPos() == player.getyPos()) {
                window.reset();
            }
        }
        for (Void void1 : voids) {
            if (void1.getxPos() == player.getxPos() && void1.getyPos() == player.getyPos()) {
                window.reset();
            }
        }
    }

    /**
     * Method to spawn beetles when there are too few of them on screen
     */
    private void spawnAdditionalBeetles() {
        if (beetles.size() < MIN_BEETLES) {
            int beetlesToSpawn = ADDITIONAL_BEETLES;

            for (Enemy[] enemy : beetleSpawn) {
                for (Enemy enemy1 : enemy) {
                    if (beetlesToSpawn == 0) {
                        break;
                    }

                    if (enemy1 != null && !isBeetleAtSpawn(enemy1)) {
                        Beetle beetle = new Beetle((int) enemy1.getxPos(), (int) enemy1.getyPos(), tileWidth, "beetle");
                        sprites.add(beetle);
                        beetles.add(beetle);
                        beetlesToSpawn--;
                    }
                }

                if (beetlesToSpawn == 0) {
                    break;
                }
            }
        }
    }

    private boolean isBeetleAtSpawn(Enemy spawn) {
        for (Beetle beetle : beetles) {
            if (beetle.getxPos() == spawn.getxPos() && beetle.getyPos() == spawn.getyPos()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to reset the topography of the level upon the player's death
     */
    public void reset() {
        sprites.clear();
        playerMoved = false;
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
