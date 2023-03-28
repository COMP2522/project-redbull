package org.Snake;

import com.mongodb.client.MongoClient;
import org.bson.Document;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBDatabase {

  private final MongoClient mongoClient;
  private final MongoDatabase database;
  private final MongoCollection<Document> collection;

  public MongoDBDatabase(String uri, String databaseName, String collectionName) {
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString(uri))
            .serverApi(ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build())
            .build();
    this.mongoClient = MongoClients.create(settings);
    this.database = mongoClient.getDatabase(databaseName);
    this.collection = database.getCollection(collectionName);
  }

  public void insertDocument(Document document) {
    collection.insertOne(document);
  }

  public void close() {
    mongoClient.close();
  }
}
