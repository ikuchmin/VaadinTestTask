package com.haulmont.testtask.repositories;

import com.haulmont.testtask.models.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}