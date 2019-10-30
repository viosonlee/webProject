package com.webproject.repositories;

import com.webproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findOneByAccount(String account);
    List<User>findOneByToken(String token);
}
