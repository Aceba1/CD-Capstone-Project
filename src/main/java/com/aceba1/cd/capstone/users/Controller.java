package com.aceba1.cd.capstone.users;

import com.aceba1.cd.capstone.utils.MapBuilder;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

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
      .sign(Algorithm.HMAC256(JWT_SECRET));
  }

  @PutMapping("${users.map.login}")
  public Object login(
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

        User user = UserDBService.getUser(filter);

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
        UserDBService.insertUser(form, true);
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
