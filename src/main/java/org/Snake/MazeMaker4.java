package org.Snake;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * MazeMaker class which generates a maze for the level
 *
 * @author
 * @version
 */
public class MazeMaker4 {
  public static void divide(int x1, int y1, int x2, int y2, Wall[][] walls, String wallImage, int cellSize) {
    if (x2 <= x1 || y2 <= y1) {
      return;
    }
  }

  static int zero = 0;

  /**
   * Method to create the maze and place the tiles
   */
  public static Wall[][] createMaze(String wallImage, int rows, int cols, int cellSize, int level) {
    Wall[][] walls = new Wall[rows][cols];

    final int numPieces = 6;

    ArrayList<JSONObject> mazeObjects = new ArrayList<JSONObject>();

    for (int i = 1; i <= numPieces; i++) {
      try {
        String fileName = "src" + File.separator + "main" + File.separator + "pieces" + File.separator + "p" + i + ".json";
        FileReader reader = new FileReader(fileName);
        StringBuffer buffer = new StringBuffer();
        int index;
        while ((index = reader.read()) != -1) {
          buffer.append((char) index);
        }
        JSONObject obj = new JSONObject(buffer.toString());
        mazeObjects.add(obj);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    //SEPERATOR
    Random rand = new Random();
    int randomIndex = rand.nextInt(mazeObjects.size());
    JSONObject piece = mazeObjects.get(randomIndex);




    for(
            int i = 0;
            i< 4;i++)

    {
      int x = rand.nextInt(cols / 4);
      int y = rand.nextInt(cols / 4);
      placePiece(walls, wallImage, cellSize, piece, x, y, rows, cols);
    }


    JSONArray maze = piece.getJSONArray("maze");


    // bottom left
    for (int j = 0; j < maze.length(); j++) {
      JSONObject cell = maze.getJSONObject(j);
      int x = cell.getInt("x");
      int y = cell.getInt("y");


    }


    for (int row = 0; row < rows; row++) {
      walls[row][0] = new Wall(row * cellSize, 0, cellSize, wallImage, true);
      walls[row][cols - 1] = new Wall(row * cellSize, (cols - 1) * cellSize, cellSize, wallImage, true);


      walls[5][5] = new Wall(5 *cellSize, 5 *cellSize, cellSize, wallImage, true);
      walls[6][6] = new Wall(6 *cellSize, 5 *cellSize, cellSize, wallImage, true);
      walls[7][7] = new Wall(7 *cellSize, 5 *cellSize, cellSize, wallImage, true);
      walls[8][8] = new Wall(8 *cellSize, 5 *cellSize, cellSize, wallImage, true);
      walls[9][9] = new Wall(9 *cellSize, 5 *cellSize, cellSize, wallImage, true);
      walls[10][10] = new Wall(10 *cellSize, 5 *cellSize, cellSize, wallImage, true);
      walls[11][11] = new Wall(11 *cellSize, 5 *cellSize, cellSize, wallImage, true);
      walls[12][12] = new Wall(12 *cellSize, 5 *cellSize, cellSize, wallImage, true);
      walls[13][13] = new Wall(13 *cellSize, 5 *cellSize, cellSize, wallImage, true);
      walls[14][14] = new Wall(14 *cellSize, 5 *cellSize, cellSize, wallImage, true);




    }
    for (int col = 0; col < cols; col++) {
      walls[0][col] = new Wall(0, col * cellSize, cellSize, wallImage, true);
      walls[rows - 1][col] = new Wall((rows - 1) * cellSize, col * cellSize, cellSize, wallImage, true);
    }

    divide(1, 1, rows - 2, cols - 2, walls, wallImage, cellSize);


    return walls;
  }


  // Choose a random piece


  public static void placePiece(Wall[][] walls, String wallImage, int cellSize, JSONObject piece, int startX, int startY
          , int rows, int cols) {


  }

}



