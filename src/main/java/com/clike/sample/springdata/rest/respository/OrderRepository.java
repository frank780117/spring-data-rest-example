package com.clike.sample.springdata.rest.respository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clike.sample.springdata.rest.entity.Customer;
import com.clike.sample.springdata.rest.entity.Order;

@RepositoryRestResource(collectionResourceRel = "order", path = "order")
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

  List<Order> findByCustomer(@Param("customer") Customer customer);
}
