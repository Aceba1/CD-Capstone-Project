package com.aceba1.cd.capstone.users.model;

import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public final class User {

  public User() { }

  public User(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
  }

  @BsonId
  public ObjectId _id;
  @BsonProperty(value = "name")
  public String name;
  @BsonProperty(value = "email")
  public String email;
  @BsonProperty(value = "password")
  public String password;

  @BsonProperty(value = "tables")
  public String[] tables;

  @BsonIgnore
  public Document getDocument(boolean includeId) {
    Document doc = new Document();

    if (name != null)
      doc.append("name", name);
    if (email != null)
      doc.append("email", email);
    if (password != null)
      doc.append("password", password);

    if (includeId) doc.append("id", _id);
    return doc;
  }

  /**
   * Should not be exposed!
   */
  @Override
  public String toString() {
    return "User{" +
      "_id=" + _id +
      ", name='" + name + '\'' +
      ", email='" + email + '\'' +
      ", password='" + password + '\'' +
      '}';
  }
}
