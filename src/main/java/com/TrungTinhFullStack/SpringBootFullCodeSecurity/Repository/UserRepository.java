package com.TrungTinhFullStack.SpringBootFullCodeSecurity.Repository;

import com.TrungTinhFullStack.SpringBootFullCodeSecurity.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    Boolean existsByUsername(String Username);
}
