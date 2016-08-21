package com.clike.sample.springdata.rest.respository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clike.sample.springdata.rest.entity.Role;

@RepositoryRestResource(collectionResourceRel = "role", path = "role")
public interface RoleRepository extends CrudRepository<Role, Long> {

}
