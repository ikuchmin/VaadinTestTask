package com.haulmont.testtask.repositories;

import com.haulmont.testtask.models.RoleType;
import com.haulmont.testtask.models.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("select u from User u left join u.role r where r.name = ?1")
    public List<User> getAllUsersWithRole(RoleType type);
}