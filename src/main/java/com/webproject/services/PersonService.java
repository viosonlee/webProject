package com.webproject.services;

import com.webproject.models.Person;
import com.webproject.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Person findOnByAccount(String account) {
        List<Person> oneByAccount = personRepository.findOneByAccount(account);
        if (oneByAccount == null || oneByAccount.isEmpty())
            return null;
        return oneByAccount.get(0);
    }

}
