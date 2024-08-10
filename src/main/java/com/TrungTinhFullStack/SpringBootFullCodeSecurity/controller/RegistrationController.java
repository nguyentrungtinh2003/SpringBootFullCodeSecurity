package com.TrungTinhFullStack.SpringBootFullCodeSecurity.controller;

import com.TrungTinhFullStack.SpringBootFullCodeSecurity.entity.User;
import com.TrungTinhFullStack.SpringBootFullCodeSecurity.event.RegistrationCompleteEvent;
import com.TrungTinhFullStack.SpringBootFullCodeSecurity.model.UserModel;
import com.TrungTinhFullStack.SpringBootFullCodeSecurity.service.UserService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel) {
      User user = userService.registerUser(userModel);
      publisher.publishEvent(new RegistrationCompleteEvent(
              user,
              "url"
      ));
      return "Register Success !";
  }
}
