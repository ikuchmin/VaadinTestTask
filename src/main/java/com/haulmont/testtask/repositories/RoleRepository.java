package com.haulmont.testtask.repositories;

import com.haulmont.testtask.models.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}