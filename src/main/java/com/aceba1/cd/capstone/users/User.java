package com.aceba1.cd.capstone.users;

import org.bson.BsonDocument;
import org.bson.BsonDocumentWrapper;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.Map;

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

  public void securePassword() {
    password = ;
  }

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
