package org.Snake;

import org.Snake.Enemies.Beetle;
import org.Snake.Enemies.Flower;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * MazeMaker class which loads the maze from a json file.
 *
 * @author
 * @version 1.0
 */
public class MazeMaker {

    /**
     *  Loads the maze from a json file.
     * @param wallImage
     * @param rows
     * @param cols
     * @param cellSize
     * @param level
     * @return
     */
    public static Wall[][] loadMaze(String wallImage, int rows,
                                    int cols, int cellSize, String level) {
        Wall[][] walls = new Wall[rows][cols];

        //read json file
        FileReader reader = null;
        try {
            reader = new FileReader("src" + File.separator
                    + "main" + File.separator + "levels" + File.separator + level + ".json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        StringBuffer buffer = new StringBuffer();
        int i;
        try {
            while ((i = reader.read()) != -1) {
                buffer.append((char) i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //create maze
        JSONObject obj = new JSONObject(buffer.toString());
        JSONArray maze = obj.getJSONArray("maze");

        for (int j = 0; j < maze.length(); j++) {
            JSONObject row = maze.getJSONObject(j);
            int x = row.getInt("y");
            int y = row.getInt("x");
            walls[x][y] = new Wall(x * cellSize, y * cellSize, cellSize ,"wall", true);
        }
        return walls;
    }

    /**
     * Method to load the spawn point from a json file.
     * @param level the name of the level
     * @return the spawn point
     */
    public static int[] loadSpawn(String level) {
        int[] spawn = new int[2];
        //read json file
        FileReader reader;
        try {
            reader = new FileReader("src" + File.separator + "main" + File.separator + "levels" + File.separator + level + ".json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        StringBuffer buffer = new StringBuffer();
        int i;
        try {
            while ((i = reader.read()) != -1) {
                buffer.append((char) i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //create maze
        JSONObject obj = new JSONObject(buffer.toString());
        JSONArray spawnArray = obj.getJSONArray("spawn");

        try {
            spawn[0] = spawnArray.getJSONObject(0).getInt("x");
            spawn[1] = spawnArray.getJSONObject(0).getInt("y");
        } catch (Exception e) {
            spawn[0] = 10;
            spawn[1] = 10;
//            System.out.println("No spawn point found, using default");
        }
        return spawn;
    }

    /**
     * Method to load the food from a json file
     * @param rows the number of rows
     * @param cols the number of columns
     * @param cellSize the size of the cells
     * @param level the name of the level
     * @return the food tiles
     */
    public static Food[][] loadFood(int rows, int cols, int cellSize, String level) {
        Food[][] foodTiles = new Food[rows][cols];

        //read json file
        FileReader reader = null;
        try {
            reader = new FileReader("src" + File.separator + "main" + File.separator + "levels" + File.separator + level + ".json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        StringBuffer buffer = new StringBuffer();
        int i;
        try {
            while ((i = reader.read()) != -1) {
                buffer.append((char) i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //create maze
        JSONObject obj = new JSONObject(buffer.toString());
        try {
            JSONArray foodArray = obj.getJSONArray("food");
            for (int j = 0; j < foodArray.length(); j++) {
                JSONObject row = foodArray.getJSONObject(j);
                int x = row.getInt("y");
                int y = row.getInt("x");
                foodTiles[x][y] = new Food(x * cellSize, y*cellSize, cellSize, "food");
            }
        } catch (JSONException e) {
            // no "food" section in JSON file
        }
        return foodTiles;
    }


    /**
     * Method to load the beetles from a json file
     * @param rows the number of rows
     * @param cols the number of columns
     * @param cellSize the size of the cells
     * @param level the name of the level
     * @return the beetle tiles
     */
    public static Beetle[][] loadBeetleSpawn(int rows, int cols, int cellSize, String level) {
        Beetle[][] beetles = new Beetle[rows][cols];

        //read json file
        FileReader reader = null;
        try {
            reader = new FileReader("src" + File.separator + "main" + File.separator + "levels" + File.separator + level + ".json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        StringBuffer buffer = new StringBuffer();
        int i;
        try {
            while ((i = reader.read()) != -1) {
                buffer.append((char) i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //create beetles
        JSONObject obj = new JSONObject(buffer.toString());
        JSONArray beetleArray = obj.optJSONArray("Beetle");

        if (beetleArray != null) {
            for (int j = 0; j < beetleArray.length(); j++) {
                JSONObject row = beetleArray.getJSONObject(j);
                int x = row.getInt("y");
                int y = row.getInt("x");
                beetles[x][y] = new Beetle(x * cellSize, y * cellSize, cellSize, "beetle");
            }
        }

        //print the number of beetles
        int numBeetles = 0;
        for (int j = 0; j < beetles.length; j++) {
            for (int k = 0; k < beetles[0].length; k++) {
                if (beetles[j][k] != null) {
                    numBeetles++;
                }
            }
        }
        System.out.println("Number of beetles: " + numBeetles);

        return beetles;
    }



/*
    public class FlowerLoader {
        public static Flower[][] loadFlowers(int rows, int cols, int cellSize, String level) {
            Flower[][] flowers = new Flower[rows][cols];

            //read json file
            FileReader reader = null;
            try {
                reader = new FileReader("src" + File.separator + "main" + File.separator + "levels" + File.separator + level + ".json");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            StringBuffer buffer = new StringBuffer();
            int i;
            try {
                while ((i = reader.read()) != -1) {
                    buffer.append((char) i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            //create flowers
            JSONObject obj = new JSONObject(buffer.toString());
            JSONArray flowerArray = obj.optJSONArray("Beetle");

            if (flowerArray != null) {
                for (int j = 0; j < flowerArray.length(); j++) {
                    JSONObject row = flowerArray.getJSONObject(j);
                    int x = row.getInt("y");
                    int y = row.getInt("x");
                    flowers[x][y] = new Flower(x * cellSize, y * cellSize, cellSize, "flower");
                }
            }

            return flowers;
        }
    }

 */

}
