package com.aceba1.cd.capstone.users.utils;

import com.aceba1.cd.capstone.users.model.LoginForm;

public class UserVerify {

  public static final int MIN_USERNAME_LENGTH = 3;
  public static final int MAX_USERNAME_LENGTH = 20;
  public static final int MIN_PASSWORD_LENGTH = 6;
  public static final int MAX_PASSWORD_LENGTH = 100;

  //TODO: Find something that already exists
  public static final String EMAIL_REGEX = "^.+@.+[:.].+$";
  public static final String USER_REGEX = "^[A-Za-z0-9_ \\-]+$";

  public static String verify(String name, String email, String password) {
    return verifyName(name)
      + verifyEmail(email)
      + verifyPassword(password);
  }

  public static String verifyName(String name) {
    return name == null ? "Name is missing\n" : (
      name.length() < MIN_USERNAME_LENGTH ? "Name is too short\n" : (
        name.length() > MAX_USERNAME_LENGTH ? "Name is too long\n" : (
          validName(name) ? "" : "Name is invalid\n"
      )));
  }

  public static String verifyEmail(String email) {
    return email == null ? "Email is missing\n" : (
      validEmail(email) ? "" : "Email is invalid\n"
    );
  }

  public static String verifyPassword(String password) {
    return password == null ? "Password is missing\n" : (
      password.length() < MIN_PASSWORD_LENGTH ? "Password is too short\n" : (
        password.length() > MAX_PASSWORD_LENGTH ? "Password is too long\n" : ""
      ));
  }

  public static boolean validEmail(String value) {
    return value != null && value.matches(EMAIL_REGEX);
  }

  public static boolean validName(String value) {
    return value != null && value.matches(USER_REGEX);
  }
}
