package com.aceba1.cd.capstone.users.controller;

import com.aceba1.cd.capstone.users.model.LoginForm;
import com.aceba1.cd.capstone.users.model.User;
import com.aceba1.cd.capstone.users.service.UserDBService;
import com.aceba1.cd.capstone.users.utils.JWTUtil;
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

  //TODO: Relocate?
  @Value("${users.jwt.secret}")
  private void setSecret(String value) {
    JWTUtil.setAlgorithm(value);
  }

  @Value("${users.jwt.hours}")
  private long JWT_HOURS;

  @Autowired
  ObjectMapper mapper;

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
        //filter.append("password", UserDBService.verifyPassword(form.password));

        User user = UserDBService.getUser(filter);

        if (user == null || !UserDBService.testPassword(form.password, user.password))
          return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new MapBuilder("message", "User not found"));

        return new MapBuilder("jwt", JWTUtil.genJWT(user, form.password, JWT_HOURS));

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
    @RequestBody User form, //TODO: Stripped USER type (No ADMIN)
    HttpServletResponse response
  ) {
    if (UserDBService.isReady()) {

      try {
        String errors = form.verify();

        if (errors.length() != 0) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new MapBuilder("message", errors));
        }
        String rawPassword = form.password;
        UserDBService.insertUser(form);

        return new MapBuilder("jwt", JWTUtil.genJWT(form, rawPassword, JWT_HOURS));

      } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.CONFLICT)
          .body(new MapBuilder("message", e.getMessage()));
      }
    }
    response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
    return "Unavailable";
  }

  @GetMapping("${users.map.verify}")
  public Object verify(
    @RequestBody String jwt
  ) {
    //TODO: Password should not be exposed by REST API!
    return JWTUtil.parseJWT(jwt);
  }
}
