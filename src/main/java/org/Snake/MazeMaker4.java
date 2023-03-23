package org.Snake;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class MazeMaker4 {
  public static void divide(int x1, int y1, int x2, int y2, Tile[][] tiles, String wallImage, int cellSize) {
    if (x2 <= x1 || y2 <= y1) {
      return;
    }
  }

  static int zero = 0;

  public static Tile[][] createMaze(String wallImage, int rows, int cols, int cellSize, int level) {
    Tile[][] tiles = new Tile[rows][cols];

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
      placePiece(tiles, wallImage, cellSize, piece, x, y, rows, cols);
    }


    JSONArray maze = piece.getJSONArray("maze");


    // bottom left
    for (int j = 0; j < maze.length(); j++) {
      JSONObject cell = maze.getJSONObject(j);
      int x = cell.getInt("x");
      int y = cell.getInt("y");


    }

    for (int row = 0; row < rows; row++) {
      tiles[row][0] = new Tile(row * cellSize, 0, cellSize, wallImage, true);
      tiles[row][cols - 1] = new Tile(row * cellSize, (cols - 1) * cellSize, cellSize, wallImage, true);


      tiles[5][5] = new Tile(5 *cellSize, 5 *cellSize, cellSize, wallImage, true);
      tiles[6][6] = new Tile(6 *cellSize, 5 *cellSize, cellSize, wallImage, true);
      tiles[7][7] = new Tile(7 *cellSize, 5 *cellSize, cellSize, wallImage, true);
      tiles[8][8] = new Tile(8 *cellSize, 5 *cellSize, cellSize, wallImage, true);
      tiles[9][9] = new Tile(9 *cellSize, 5 *cellSize, cellSize, wallImage, true);
      tiles[10][10] = new Tile(10 *cellSize, 5 *cellSize, cellSize, wallImage, true);
      tiles[11][11] = new Tile(11 *cellSize, 5 *cellSize, cellSize, wallImage, true);
      tiles[12][12] = new Tile(12 *cellSize, 5 *cellSize, cellSize, wallImage, true);
      tiles[13][13] = new Tile(13 *cellSize, 5 *cellSize, cellSize, wallImage, true);
      tiles[14][14] = new Tile(14 *cellSize, 5 *cellSize, cellSize, wallImage, true);




    }
    for (int col = 0; col < cols; col++) {
      tiles[0][col] = new Tile(0, col * cellSize, cellSize, wallImage, true);
      tiles[rows - 1][col] = new Tile((rows - 1) * cellSize, col * cellSize, cellSize, wallImage, true);
    }

    divide(1, 1, rows - 2, cols - 2, tiles, wallImage, cellSize);


    return tiles;
  }


  // Choose a random piece


  public static void placePiece(Tile[][] tiles, String wallImage, int cellSize, JSONObject piece, int startX, int startY
          , int rows, int cols) {


  }

}



