package org.Snake;

import org.bson.Document;

public class MongoDBMain {

  public static void main(String[] args) {
    MongoDBDatabase db = new MongoDBDatabase("mongodb+srv://testUser:KRfCne1nAlODELEy@cluster0.0hvtvxa.mongodb.net/?retryWrites=true&w=majority", "test", "leaderboard");

    // Insert the leaderboard data
    db.insertDocument(new Document("name", "Cameron").append("score", 10));
    db.insertDocument(new Document("name", "Kale").append("score", 5));
    db.insertDocument(new Document("name", "Adam").append("score", 1));

    // Close the database
    db.close();
  }
}
