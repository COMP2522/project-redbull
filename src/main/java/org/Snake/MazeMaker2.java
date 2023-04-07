package org.Snake;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * MazeMaker class which loads the maze from a json file, likely broken at the moment
 *
 * @author
 * @version 1.0
 */
public class MazeMaker2 {
  public static Wall[][] loadMaze(String wallImage, int rows, int cols, int cellSize, int level) {
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

    // Choose a random piece
    Random rand = new Random();
    int randomIndex = rand.nextInt(mazeObjects.size());
    JSONObject piece = mazeObjects.get(randomIndex);



    for (int i = 0 ; i < 4; i++) {
      int x = rand.nextInt(cols / 4);
      int y = rand.nextInt(cols / 4);
      placePiece(walls, wallImage, cellSize, piece, x, y, rows, cols);
    }

    return walls;
  }

  /**
   * Method to place the maze piece in the maze
   */
  public static void placePiece(Wall[][] walls, String wallImage, int cellSize, JSONObject piece, int startX, int startY
  , int rows, int cols) {
    JSONArray maze = piece.getJSONArray("maze");
    int offSet = 3;
    // top left
    for (int j = 0; j < maze.length(); j++) {
      JSONObject cell = maze.getJSONObject(j);
      int x = cell.getInt("x");
      int y = cell.getInt("y");
      walls[startX + x][startY + y] = new Wall((startX + x) * cellSize, (startY + y) * cellSize, cellSize, wallImage, true);
    }
    // top right
    for (int j = 0; j < maze.length(); j++) {
      JSONObject cell = maze.getJSONObject(j);
      int x = cell.getInt("x");
      int y = cell.getInt("y");
      walls[cols - x - offSet][startY + y] = new Wall((cols - x - offSet) * cellSize, (startY + y) * cellSize, cellSize, wallImage, true);
    }

    // bottom left
    for (int j = 0; j < maze.length(); j++) {
      JSONObject cell = maze.getJSONObject(j);
      int x = cell.getInt("x");
      int y = cell.getInt("y");
      walls[startX + x][rows - y - offSet] = new Wall((startX + x) * cellSize, (rows - y - offSet) * cellSize, cellSize, wallImage, true);
    }
    // bottom right
    for (int j = 0; j < maze.length(); j++) {
      JSONObject cell = maze.getJSONObject(j);
      int x = cell.getInt("x");
      int y = cell.getInt("y");
      walls[cols - x - 1][rows - y - offSet] = new Wall ((cols - x - 1) * cellSize, (rows - y - offSet) * cellSize, cellSize, wallImage, true);
    }
  }




}
