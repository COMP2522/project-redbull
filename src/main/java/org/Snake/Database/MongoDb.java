package org.Snake.Database;

// Imports
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import org.bson.Document;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import static com.mongodb.client.model.Filters.eq;

/**
 * The MongoDb class communicates with our database.
 *
 * @author Cameron Fung
 */
public class MongoDb {

  //Instantiates the database variable.
  MongoDatabase database;

  /**
   * The MongoDb method is what is actually doing the connecting to the database.
   *
   * This ensures we can connect to our MongoDb database.
   */
  public MongoDb() {
    ConnectionString connectionString = new ConnectionString("mongodb+srv://MasterUser:AuOhjOhAmZbmiqG1@cluster0.cre2ejv.mongodb.net/?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .serverApi(ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build())
            .build();
    MongoClient mongoClient = MongoClients.create(settings);
    database = mongoClient.getDatabase("test");
    try {
      database.createCollection("players");
    } catch (Exception e) {
      System.out.println("Collection already in database.");
    }
  }

  /**
   * This put method puts data into our database.
   *
   * @param name - The name of the user.
   * @param playerScore - The score the player achieved.
   * @param level - The level which the user was playing.
   */
  public void put(String name, int playerScore, String level) {
    ArrayList<KVPair> scores = get(level);
    if (scores.size() >= 10) {
      if (scores.get(9).value < playerScore) {
        Document doc = new Document();
        doc.append("name", name);
        doc.append("score", playerScore);
        database.getCollection(level).insertOne(doc);
        database.getCollection(level).deleteOne(eq("name", scores.get(9).key));
      }
    } else {
      Document doc = new Document();
      doc.append("name", name);
      doc.append("score", playerScore);
      database.getCollection(level).insertOne(doc);
    }
  }

  /**
   * Getter to retrieve data from the database.
   *
   * @param levelName - The level name we want the data for
   * @return - Returns the scores of all users who have attempted the level.
   */
  public ArrayList<KVPair> get(String levelName) {
    FindIterable<Document> res = database.getCollection(levelName).find();

    ArrayList<KVPair> scores = new ArrayList<>();

    res.forEach((d) -> {
      scores.add(new KVPair((String) d.get("name"),(int) d.get("score")));
    });

    Collections.sort(scores);
    return scores;
  }

  /**
   * Checks if a user has obtained a high score after they have completed a snake game.
   *
   * @param score - The score the player obtained.
   * @param level - The level the user was playing.
   * @return Returns true if the player obtained the high score.
   */
  public boolean isHighScore(int score, String level) {
    ArrayList<KVPair> scores = get(level);
    if (scores.size() < 10) {
      return true;
    } else {
      return scores.get(9).getValue() < score;
    }
  }

  /**
   * Main method that drives the MongoDb class.
   *
   * @param args - Unused.
   */
  public static void main(String[] args) {
    MongoDb db = new MongoDb();

    ArrayList<KVPair> scores = db.get("classic");
  }
}