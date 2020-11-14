package com.aceba1.cd.capstone.users;

import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
public class Controller {

  @PutMapping("/user/login")
  public String login(
    @RequestBody LoginForm form,
    HttpServletResponse response
  ) {
    if (UserDBService.isReady()) {
      //TODO: Verify User
      //TODO: Compare to secured password
      //TODO: Return JWT
      return UserDBService.getUsers().find(new Document("name", form.credential)).first().email;
    }
    response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
    return "Unavailable";
  }

  @PostMapping("/user/register")
  public String register(
    @RequestBody User form,
    HttpServletResponse response
  ) {
    if (UserDBService.isReady()) {
      //TODO: Verify User
      //TODO: Secure the password
      //TODO: Return JWT
      UserDBService.insertUser(form);
      return form.id.toString();
    }
    response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
    return "Unavailable";
  }
}
