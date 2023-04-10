package org.Snake.Database;

/**
 * Key value pair class that implements Comparable.
 *
 * @author Cameron Fung
 */
public class KVPair implements Comparable<KVPair>{

  // Initializes the key variable.
  String key;

  // Initializes the value variable that is an int.
  int value;

  /**
   * Key value pair method.
   *
   * @param key - String key
   * @param value - integer value
   */
  public KVPair(String key, int value) {
    this.key = key;
    this.value = value;
  }


  /**
   * Compareto method
   *
   * @param k the object to be compared.
   * @return Value in descending order.
   */
  @Override
  public int compareTo(KVPair k) {
    if (k == null) {
      throw new Error();
    }
    // want to sort by value in descending order
    return ((KVPair) k).value - this.value;
  }

  /**
   * toString method to return the key and value.
   *
   * @return - Returns the key and the value
   */
  public String toString() {
    return key + " " + value;
  }

  /**
   * Getter for the key.
   *
   * @return - Returns the key.
   */
  public String getKey() {
    return key;
  }

  /**
   * Getter for the value.
   *
   * @return - Returns the value.
   */
  public int getValue() {
    return value;
  }
}
