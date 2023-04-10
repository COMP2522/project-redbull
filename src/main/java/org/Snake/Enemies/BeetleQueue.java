package org.Snake.Enemies;


import java.util.Iterator;

public class BeetleQueue implements Iterable<Beetle> {
  private static class Node {
    private final Beetle beetle;
    private Node next;

    public Node(Beetle beetle) {
      this.beetle = beetle;
    }
  }

  private Node head, tail;
  private int size;

  public synchronized void add(Beetle beetle) {
    Node newNode = new Node(beetle);
    if (tail != null) {
      tail.next = newNode;
    } else {
      head = newNode;
    }
    tail = newNode;
    size++;
  }

  public synchronized void remove(Beetle beetle) {
    Node current = head;
    Node previous = null;

    while (current != null) {
      if (current.beetle == beetle) {
        if (previous != null) {
          previous.next = current.next;
        } else {
          head = current.next;
        }

        if (tail == current) {
          tail = previous;
        }

        size--;
        break;
      }
      previous = current;
      current = current.next;
    }
  }

  public synchronized int size() {
    return size;
  }

  @Override
  public Iterator<Beetle> iterator() {
    return new Iterator<Beetle>() {
      private Node currentNode = head;

      @Override
      public boolean hasNext() {
        return currentNode != null;
      }

      @Override
      public Beetle next() {
        Beetle beetle = currentNode.beetle;
        currentNode = currentNode.next;
        return beetle;
      }
    };
  }
}

