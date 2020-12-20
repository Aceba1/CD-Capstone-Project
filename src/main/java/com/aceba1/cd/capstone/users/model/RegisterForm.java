package com.aceba1.cd.capstone.users.model;

import com.aceba1.cd.capstone.users.utils.UserVerify;

public class RegisterForm {

  public String name;
  public String email;
  public String password;

  public String verify() {
    return UserVerify.verify(name, email, password);
  }

  public User makeUser() {
    return new User(name, email, password);
  }
}
