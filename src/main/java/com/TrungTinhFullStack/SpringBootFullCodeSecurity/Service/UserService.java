package com.TrungTinhFullStack.SpringBootFullCodeSecurity.Service;

import com.TrungTinhFullStack.SpringBootFullCodeSecurity.Entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    public Page<User> getUserByPage(int page, int size);
}
