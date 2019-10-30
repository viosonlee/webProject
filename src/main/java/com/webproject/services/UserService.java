package com.webproject.services;

import com.mysql.cj.util.StringUtils;
import com.webproject.models.Person;
import com.webproject.models.User;
import com.webproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUserByAccount(String account) {
        if (StringUtils.isNullOrEmpty(account)) {
            return null;
        }
        List<User> oneByAccount = userRepository.findOneByAccount(account);
        if (oneByAccount == null || oneByAccount.size() == 0)
            return null;
        return oneByAccount.get(0);
    }

    public User findUserByToken(String token) {
        if (StringUtils.isNullOrEmpty(token))
            return null;
        List<User> one = userRepository.findOneByToken(token);
        if (one == null || one.size() == 0) return null;
        return one.get(0);
    }



    public User save(User user) {
        return userRepository.save(user);
    }
}

