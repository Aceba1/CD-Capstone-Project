package com.aceba1.cd.capstone.users;

import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class Controller {

  @PutMapping("${users.map.login}")
  public String login(
    @RequestBody LoginForm form,
    HttpServletResponse response
  ) {
    if (UserDBService.isReady()) {
      //TODO: Verify User
      //TODO: Ensure user only passes secured password
      //- (remove securePassword(), encrypt on send)
      try {
        String credential = form.isCredentialEmail() ? "username" : "email";

        Document filter = new Document(credential, form.credential);
        filter.append("password", UserDBService.securePassword(form.password));

        return UserDBService.getUser(filter).email; //TODO: Return JWT
      } catch(Exception E) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return "Not found";
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

      //TODO: Return JWT
      UserDBService.insertUser(form, true);
      return form.id.toString();
    }
    response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
    return "Unavailable";
  }
}
