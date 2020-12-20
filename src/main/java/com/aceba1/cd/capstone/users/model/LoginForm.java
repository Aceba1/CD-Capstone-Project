package com.aceba1.cd.capstone.users.model;

import com.aceba1.cd.capstone.users.utils.UserVerify;

public class LoginForm {

  public String credential;
  public String password;

  public boolean isCredentialEmail() {
    return UserVerify.validEmail(credential);
  }
}
