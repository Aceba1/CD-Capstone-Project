package com.aceba1.cd.capstone.users.model;

public class RegisterForm {
  public String name;
  public String email;
  public String password;

  public String verify() {
    return (name == null ? "Name is missing\n" : (
      name.length() < User.MIN_USERNAME_LENGTH ? "Name is too short\n" : (
        name.length() > User.MAX_USERNAME_LENGTH ? "Name is too long\n" : ""
      ))) +
      (password == null ? "Password is missing\n" : (
        password.length() < User.MIN_PASSWORD_LENGTH ? "Password is too short\n" : (
          password.length() > User.MAX_PASSWORD_LENGTH ? "Password is too long\n" : ""
        ))) +
      (email == null ? "Email is missing\n" : (
        LoginForm.isEmail(email) ? "" : "Email is invalid\n"
      ));
  }

  public User makeUser() {
    return new User(name, email, password);
  }
}
