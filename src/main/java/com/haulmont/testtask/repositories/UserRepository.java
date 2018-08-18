package com.haulmont.testtask.repositories;

import com.haulmont.testtask.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}