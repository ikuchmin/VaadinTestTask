package com.haulmont.testtask.repositories;

import com.haulmont.testtask.models.RoleType;
import com.haulmont.testtask.models.entities.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long> {
    List<Role> findByName(RoleType name);
}