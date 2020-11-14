package com.aceba1.cd.capstone.users;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
public class Controller {

  @PutMapping("/user/login")
  public String main(
    HttpServletResponse response
  ) {
    return "OK";
  }
}
