package org.Snake.Database;

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

public class MongoDb {
  MongoDatabase database;

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
  };

  public ArrayList<KVPair> get(String levelName) {
    FindIterable<Document> res = database.getCollection(levelName).find();

    ArrayList<KVPair> scores = new ArrayList<>();

    res.forEach((d) -> {
      scores.add(new KVPair((String) d.get("name"),(int) d.get("score")));
    });

    Collections.sort(scores);
    return scores;
  }
}