package com.aceba1.cd.capstone.users;

import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class Controller {

  @PutMapping("${users.map.login}")
  public String login(
    @RequestBody LoginForm form,
    HttpServletResponse response
  ) {
    if (UserDBService.isReady()) {
      //TODO: Verify User
      //TODO: Compare to secured password
      //TODO: Return JWT
      try {
        return UserDBService.getUser(new Document("name", form.credential)).email;
      } catch {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return
      }
    }
    response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
    return "Unavailable";
  }

  @PostMapping("${users.map.register}")
  public String register(
    @RequestBody User form,
    HttpServletResponse response
  ) {
    if (UserDBService.isReady()) {
      //TODO: Verify User
      //TODO: Secure the password
      //TODO: Return JWT
      UserDBService.insertUser(form, true);
      return form.id.toString();
    }
    response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
    return "Unavailable";
  }
}
