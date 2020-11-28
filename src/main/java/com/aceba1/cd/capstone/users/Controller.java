package com.aceba1.cd.capstone.users;

import com.aceba1.cd.capstone.utils.MapBuilder;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class Controller {

  @Value("${users.jwt.secret}")
  String JWT_SECRET;

  @Autowired
  ObjectMapper mapper;

  private String createJWT(User user) {
    return JWT.create()
      //.withExpiresAt(new Date()) //TODO: Figure out Date class? Determine if expiry is necessary
      .withClaim("name", user.name)
      .withClaim("password", user.password)
      .withClaim("email", user.email)
      .withClaim("id", user.id.toString())
      .sign(Algorithm.HMAC256(JWT_SECRET));
  }

  @PutMapping("${users.map.login}")
  public Object login(
    @RequestBody LoginForm form,
    HttpServletResponse response
  ) {
    if (UserDBService.isReady()) {
      //TODO: Verify User
      try {
        String credential = form.isCredentialEmail() ? "email" : "name";

        Document filter = new Document(credential, form.credential);

        filter.append("password", UserDBService.verifyPassword(form.password));

        System.out.println(filter.toString());

        User user = UserDBService.getUser(filter);
        if (user == null)
          return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new MapBuilder("message", "User not found"));

        return new MapBuilder("jwt", createJWT(user));

      } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new MapBuilder("message", "User not found"));
      }
    }
    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
      .body(new MapBuilder("message", "Service not available"));
  }

  @PostMapping("${users.map.register}")
  public Object register(
    @RequestBody User form,
    HttpServletResponse response
  ) {
    if (UserDBService.isReady()) {

      try {
        String errors = form.verify();

        if (errors.length() != 0) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new MapBuilder("message", errors));
        }

        UserDBService.insertUser(form);
        return new MapBuilder("jwt", createJWT(form));

      } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.CONFLICT)
          .body(new MapBuilder("message", e.getMessage()));
      }
    }
    response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
    return "Unavailable";
  }


}
