package com.clike.sample.springdata.rest.respository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clike.sample.springdata.rest.entity.Customer;
import com.clike.sample.springdata.rest.entity.EmailAddress;

@RepositoryRestResource(collectionResourceRel = "custom", path = "custom")
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

  Customer findByEmailAddress(@Param("emailAddress") EmailAddress emailAddress);
}
