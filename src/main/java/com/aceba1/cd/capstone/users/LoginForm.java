package com.aceba1.cd.capstone.users;

import java.util.regex.Pattern;

public class LoginForm {
  public static final String EMAIL_REGEX = ".*@.*[:.].*";

  public String credential;
  public String password;

  public boolean isCredentialEmail() {
    return credential.matches(EMAIL_REGEX);
  }
}
