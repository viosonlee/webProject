package com.webproject.repositories;


import com.webproject.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findOneByAccount(String account);
}
