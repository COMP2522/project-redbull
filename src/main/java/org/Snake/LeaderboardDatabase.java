package org.Snake;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

// user name : MasterUser
// pass : AuOhjOhAmZbmiqG1

public class LeaderboardDatabase {

//  private final MongoClient mongoClient;
//  private final MongoDatabase database;
//  private final MongoCollection<Document> collection;

  public LeaderboardDatabase(String uri, String databaseName, String collectionName) {


  }

  public static void main(String[] args) {
    ConnectionString connectionString = new ConnectionString("mongodb+srv://MasterUser:AuOhjOhAmZbmiqG1@cluster0.cre2ejv.mongodb.net/?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .serverApi(ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build())
            .build();
    MongoClient mongoClient = MongoClients.create(settings);
    MongoDatabase database = mongoClient.getDatabase("test");
    Document document = new Document();
    document.append("name", "Ram");
    document.append("age", 26);
    document.append("city", "Hyderabad");
    database.getCollection("students").insertOne(document);
    Document find = database.getCollection("students").find(eq("name", "Ram")).first();
    System.out.println(find);
  }
}
