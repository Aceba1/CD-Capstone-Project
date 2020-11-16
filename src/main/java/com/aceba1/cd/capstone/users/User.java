package com.aceba1.cd.capstone.users;

import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class User  {

  public User() { }

  public User(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
  }

  @BsonId
  public ObjectId id;
  @BsonProperty(value = "name")
  public String name;
  @BsonProperty(value = "email")
  public String email;
  @BsonProperty(value = "password")
  public String password;

  public Document getDocument(boolean includeId) {
    Document doc = new Document();

    if (name != null)
      doc.append("name", name);
    if (email != null)
      doc.append("email", email);
    if (password != null)
      doc.append("password", password);

    if (includeId) doc.append("id", id);
    return doc;
  }
}
