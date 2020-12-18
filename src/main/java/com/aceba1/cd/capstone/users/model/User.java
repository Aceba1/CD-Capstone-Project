package com.aceba1.cd.capstone.users.model;

import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public final class User {

  public static final int MIN_USERNAME_LENGTH = 3;
  public static final int MAX_USERNAME_LENGTH = 20;
  public static final int MIN_PASSWORD_LENGTH = 6;
  public static final int MAX_PASSWORD_LENGTH = 100;

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

  public String verify() {
    return (name == null ? "Name is missing\n" : (
        name.length() < MIN_USERNAME_LENGTH ? "Name is too short\n" : (
          name.length() > MAX_USERNAME_LENGTH ? "Name is too long\n" : ""
      ))) +
      (password == null ? "Password is missing\n" : (
        password.length() < MIN_PASSWORD_LENGTH ? "Password is too short\n" : (
          password.length() > MAX_PASSWORD_LENGTH ? "Password is too long\n" : ""
      ))) +
      (email == null ? "Email is missing\n" : (
        LoginForm.isEmail(email) ? "" : "Email is invalid\n"
      ));
  }

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
