package org.example;

/**
 * The Snake class.
 */
public class Snake {


  private class scale{
    public int x;
    public int y;
    public scale next;
  }

  public scale eatFood(scale head){
    scale curr = head;
    while(curr != null){
      curr = curr.next;
    }
    scale tail = curr.next;
    /*
    use the timer to set the tail to appear as the snake moves to the tile after it has eaten food
    the end of the tail appears one tick after the snake has eaten food to grow the snake
     */
    tail.x = curr.x;
    tail.y = curr.y;
    return head;
  }

  /*
  the initial scale values for the x and y position of the snake
  I am imagining are made upon the setup, but I'm happy to write more of it
   */
  private scale head = new scale();
  private scale scale2 = new scale();
  private scale scale3 = new scale();


}
