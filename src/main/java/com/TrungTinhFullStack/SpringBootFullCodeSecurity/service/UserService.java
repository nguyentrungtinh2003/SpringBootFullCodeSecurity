package com.TrungTinhFullStack.SpringBootFullCodeSecurity.service;

import com.TrungTinhFullStack.SpringBootFullCodeSecurity.entity.User;
import com.TrungTinhFullStack.SpringBootFullCodeSecurity.model.UserModel;

public interface UserService {

    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token,User user);
}
