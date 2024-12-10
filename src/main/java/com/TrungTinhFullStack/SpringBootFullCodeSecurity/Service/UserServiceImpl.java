package com.TrungTinhFullStack.SpringBootFullCodeSecurity.Service;

import com.TrungTinhFullStack.SpringBootFullCodeSecurity.Entity.User;
import com.TrungTinhFullStack.SpringBootFullCodeSecurity.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public Page<User> getUserByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return userRepository.findAll(pageable);
    }
}
