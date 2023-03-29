package org.Snake;

/**
 * Class for loading and storing the maze tiles
 *
 * @author
 * @version
 */
public class MazeMaker3 {

  /**
   * Method to generate a maze using the recursive division algorithm
   * @param wallImage the image to use for the walls
   * @param rows the number of rows in the maze
   * @param cols the number of columns in the maze
   * @param cellSize the size of each cell in the maze
   * @return the maze
   */
  public static Tile[][] generateMaze(String wallImage, int rows, int cols, int cellSize) {
    Tile[][] tiles = new Tile[rows][cols];

    // create outer walls
    for (int row = 0; row < rows; row++) {
      tiles[row][0] = new Tile(row*cellSize, 0, cellSize, wallImage, true);
      tiles[row][cols-1] = new Tile(row*cellSize, (cols-1)*cellSize, cellSize, wallImage, true);
    }
    for (int col = 0; col < cols; col++) {
      tiles[0][col] = new Tile(0, col*cellSize, cellSize, wallImage, true);
      tiles[rows-1][col] = new Tile((rows-1)*cellSize, col*cellSize, cellSize, wallImage, true);
    }

    divide(1, 1, rows-2, cols-2, tiles, wallImage, cellSize);

    return tiles;
  }

  /**
   * Recursive method to divide the maze into sub-rectangles
   * @param x1 the x coordinate of the top left corner of the rectangle
   * @param y1 the y coordinate of the top left corner of the rectangle
   * @param x2 the x coordinate of the bottom right corner of the rectangle
   * @param y2 the y coordinate of the bottom right corner of the rectangle
   * @param tiles the maze
   * @param wallImage the image to use for the walls
   * @param cellSize the size of each cell in the maze
   */
  public static void divide(int x1, int y1, int x2, int y2, Tile[][] tiles, String wallImage, int cellSize) {
    if (x2 <= x1 || y2 <= y1) {
      return;
    }

    // determine orientation of wall to be added
    boolean horizontal = (Math.random() < 0.5);

    // determine where to place the wall
    int wallX = horizontal ? (int) (Math.random() * (x2 - x1)) + x1 : x1;
    int wallY = horizontal ? y1 : (int) (Math.random() * (y2 - y1)) + y1;

    // determine where to create the passage
    int passageX = horizontal ? wallX : (int) (Math.random() * (x2 - x1 + 1)) + x1;
    int passageY = horizontal ? (int) (Math.random() * (y2 - y1 + 1)) + y1 : wallY;

    // create wall and passage
    for (int i = Math.min(wallX, passageX); i <= Math.max(wallX, passageX); i++) {
      tiles[i][wallY] = new Tile(i*cellSize, wallY*cellSize, cellSize, wallImage, true);
    }
    for (int j = Math.min(wallY, passageY); j <= Math.max(wallY, passageY); j++) {
      tiles[passageX][j] = new Tile(passageX*cellSize, j*cellSize, cellSize, wallImage, true);
    }

    // recursively divide the sub-rectangles
    divide(x1, y1, wallX-1, wallY-1, tiles, wallImage, cellSize);
    divide(wallX+1, wallY+1, x2, y2, tiles, wallImage, cellSize);
    divide(x1, wallY+1, wallX-1, y2, tiles, wallImage, cellSize);
    divide(wallX+1, y1, x2, wallY-1, tiles, wallImage, cellSize);
  }

}
