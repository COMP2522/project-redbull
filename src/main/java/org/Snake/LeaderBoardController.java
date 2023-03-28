package org.Snake;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import org.bson.Document;
import org.json.JSONObject;

import java.util.*;

import static com.mongodb.client.model.Filters.eq;

// user name : MasterUser
// pass : AuOhjOhAmZbmiqG1

public class LeaderBoardController {

//  private final MongoClient mongoClient;
//  private final MongoDatabase database;
//  private final MongoCollection<Document> collection;

  private void getDB() {

  }

  public static void writeHighScore(String name, int score) {
    // Connect to the MongoDB server and get a reference to the database
    ConnectionString connectionString = new ConnectionString("mongodb+srv://MasterUser:AuOhjOhAmZbmiqG1@cluster0.cre2ejv.mongodb.net/?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .serverApi(ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build())
            .build();
    MongoClient mongoClient = MongoClients.create(settings);
    MongoDatabase database = mongoClient.getDatabase("test");
    // Get a reference to the high scores collection
    MongoCollection<Document> highScoresCollection = database.getCollection("HighScores");

    // Find the lowest score in the top 10 high scores
    List<Map<String, Object>> highScoresList = readHighScores();
    int lowestTopScore = Integer.MAX_VALUE;
    if (highScoresList.size() >= 10) {
      lowestTopScore = (int) highScoresList.get(9).get("score");
    }

    // Only insert the new high score if it is in the top 10
    if (score > lowestTopScore || highScoresList.size() < 10) {
      // Remove the document with the lowest top score, if there are already 10 high scores
      if (highScoresList.size() >= 10) {
        String lowestTopName = (String) highScoresList.get(9).get("name");
        highScoresCollection.deleteOne(new Document("name", lowestTopName));
      }
      // Add the new high score
      Document newHighScore = new Document("name", name)
              .append("score", score);
      highScoresCollection.insertOne(newHighScore);
      System.out.println("New high score added!");
    } else {
      System.out.println("Sorry, your score is not high enough to make the top 10.");
    }
  }




  public static List<Map<String, Object>> readHighScores() {
    // Connect to the MongoDB server and get a reference to the database
    ConnectionString connectionString = new ConnectionString("mongodb+srv://MasterUser:AuOhjOhAmZbmiqG1@cluster0.cre2ejv.mongodb.net/?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .serverApi(ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build())
            .build();
    MongoClient mongoClient = MongoClients.create(settings);
    MongoDatabase database = mongoClient.getDatabase("test");
    // Get a reference to the high scores collection
    MongoCollection<Document> highScoresCollection = database.getCollection("HighScores");

    // Find all documents in the collection and convert them to a list of key-value pairs
    FindIterable<Document> documents = highScoresCollection.find();
    List<Map<String, Object>> highScoresList = new ArrayList<>();
    for (Document document : documents) {
      Map<String, Object> highScore = new HashMap<>();
      highScore.put("name", document.getString("name"));
      highScore.put("score", document.getInteger("score"));
      highScoresList.add(highScore);
    }

    Collections.sort(highScoresList, new Comparator<Map<String, Object>>() {
      @Override
      public int compare(Map<String, Object> o1, Map<String, Object> o2) {
        Integer score1 = (Integer) o1.get("score");
        Integer score2 = (Integer) o2.get("score");
        return score2.compareTo(score1);
      }
    });

    for (Map<String, Object> highScore : highScoresList) {
      System.out.println("Name: " + highScore.get("name") + " - Score: " + highScore.get("score"));
    }
    return highScoresList;
  }


  public static void main(String[] args) {
    for (int i = 0 ; i < 11; i ++) {
      writeHighScore(""+i, (int)(Math.random() * 100));
    }
  }
}
