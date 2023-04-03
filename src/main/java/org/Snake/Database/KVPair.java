package org.Snake.Database;

public class KVPair implements Comparable{
  String key;
  int value;

  public KVPair(String key, int value) {
    this.key = key;
    this.value = value;
  }


  @Override
  public int compareTo(Object k) {
    if (!(k instanceof KVPair)) {
      throw new Error();
    }
    // want to sort by value in descending order
    return ((KVPair) k).value - this.value;
  }

  public String toString() {
    return key + " " + value;
  }
}
