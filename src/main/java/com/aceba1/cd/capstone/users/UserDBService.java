package com.aceba1.cd.capstone.users;

import com.aceba1.cd.capstone.utils.BCrypt;
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
  @Value("${users.mongodb.uri}")
  private void setMongodbUri(String value) { UserDBService.MONGODB_URI = new ConnectionString(value); }
  @Value("${users.mongodb.db}")
  private void setMongodbDb(String value) { UserDBService.MONGODB_DB = value; }
  @Value("${users.mongodb.collection}")
  private void setMongodbCollection(String value) { UserDBService.MONGODB_COLLECTION = value; }
  @Value("${users.salt}")
  private void setHashSalt(String value) { UserDBService.SALT = value; }

  private static ConnectionString MONGODB_URI;
  private static String MONGODB_DB;
  private static String MONGODB_COLLECTION;
  private static String SALT;

  private static MongoClient client;
  private static MongoCollection<User> users;

  public static MongoCollection<User> getUsers() {
    return users;
  }

  public static String verifyPassword(String password) {
    if (password.startsWith(SALT))
      return password;

    return securePassword(password);
  }

  public static String securePassword(String password) {
    return BCrypt.hashpw(password, SALT);
  }

  public static User getUser(Document filter) {
    return users.find(filter).first();
  }

  public static void insertUser(User user) throws Exception {
    if (users.countDocuments(new Document("email", user.email)) != 0)
      throw new Exception("Email is already in use!");

    if (users.countDocuments(new Document("name", user.name)) != 0)
            throw new Exception("Name is already in use!");

    user.password = verifyPassword(user.password);

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
    client = MongoClients.create(generateSettings());
    System.out.println("---- USERS ---- Connected to MongoDB!");
    var database = client.getDatabase(MONGODB_DB);
    System.out.println("---- USERS ---- Connected to Database!");
    users = database.getCollection(MONGODB_COLLECTION, User.class);
    System.out.println("---- USERS ---- Connected to Collection!");
  }

  public static void connect() {
    storeDatabase();

    System.out.println("---- USERS ---- User Count: " + users.estimatedDocumentCount());
    ready = true;
  }
}
