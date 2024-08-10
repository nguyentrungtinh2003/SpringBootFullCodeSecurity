package com.TrungTinhFullStack.SpringBootFullCodeSecurity.event.listener;

import com.TrungTinhFullStack.SpringBootFullCodeSecurity.entity.User;
import com.TrungTinhFullStack.SpringBootFullCodeSecurity.event.RegistrationCompleteEvent;
import com.TrungTinhFullStack.SpringBootFullCodeSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import java.util.UUID;

public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token,user);
    }
}
