package com.TrungTinhFullStack.SpringBootFullCodeSecurity.Service;

import com.TrungTinhFullStack.SpringBootFullCodeSecurity.Entity.User;
import com.TrungTinhFullStack.SpringBootFullCodeSecurity.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));

        // Map the user's role to SimpleGrantedAuthority
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());

        // Return a UserDetails object with username, password, and authorities
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), List.of(authority));
    }

}
