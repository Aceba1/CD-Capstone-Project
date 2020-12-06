package com.aceba1.cd.capstone.users;

import java.util.regex.Pattern;

public class LoginForm {
  //TODO: Find something that already exists
  public static final String EMAIL_REGEX = ".*@.*[:.].+";

  public String credential;
  public String password;

  public static boolean isEmail(String value) {
    return value != null && value.matches(EMAIL_REGEX);
  }

  public boolean isCredentialEmail() {
    return isEmail(credential);
  }
}
