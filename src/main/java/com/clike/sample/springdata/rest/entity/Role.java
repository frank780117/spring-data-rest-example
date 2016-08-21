package com.clike.sample.springdata.rest.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.AccessType.Type;

@Entity
@Table(name = "Role")
public class Role extends AbstractEntity {

  @Column(nullable = false)
  private String name;

  @ElementCollection
  private List<String> accessPage = new ArrayList<String>();

  @OneToMany(mappedBy = "role")
  @AccessType(Type.PROPERTY)
  private Set<Customer> customers = new HashSet<Customer>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getAccessPage() {
    return accessPage;
  }

  public void setAccessPage(List<String> accessPage) {
    this.accessPage = accessPage;
  }

  public Set<Customer> getCustomers() {
    return customers;
  }

  public void setCustomers(Set<Customer> customers) {
    customers.forEach(e -> e.setRole(this));
    this.customers = customers;
  }

}
