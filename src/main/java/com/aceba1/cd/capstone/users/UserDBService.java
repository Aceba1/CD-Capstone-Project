package com.aceba1.cd.capstone.users;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@RestController
public class UserDBService {

  // Get values from application.properties
  @Value("${mongodb.uri}")
  private void setMongodbUri(String value) { UserDBService.MONGODB_URI = new ConnectionString(value); }
  @Value("${mongodb.db}")
  private void setMongodbDb(String value) { UserDBService.MONGODB_DB = value; }
  @Value("${mongodb.collection}")
  private void setMongodbCollection(String value) { UserDBService.MONGODB_COLLECTION = value; }

  private static ConnectionString MONGODB_URI;
  private static String MONGODB_DB;
  private static String MONGODB_COLLECTION;

  private static MongoClient client;
  private static MongoCollection<User> users;

  public static MongoCollection<User> getUsers() {
    return users;
  }

  public static User getUser(Document filter) {
    return users.find(filter).first();
  }

  public static void insertUser(User user) {
    users.insertOne(user);
  }

  public static void removeUser(Document filter) {
    users.deleteOne(filter);
  }

  private static boolean ready;

  public static boolean isReady() {
    return ready;
  }

  private static MongoClientSettings generateSettings() {
    return MongoClientSettings.builder()
      .applyConnectionString(MONGODB_URI)
      .codecRegistry(
        fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
          fromProviders(PojoCodecProvider.builder().automatic(true).build())))
      .build();
  }

  private static void storeDatabase() {
    users = MongoClients.create(generateSettings())
      .getDatabase(MONGODB_DB)
      .getCollection(MONGODB_COLLECTION, User.class);
  }

  public static void connect() {
    storeDatabase();
    System.out.println("---- MONGODB ---- User Count: " + users.countDocuments());

    ready = true;
  }
}
