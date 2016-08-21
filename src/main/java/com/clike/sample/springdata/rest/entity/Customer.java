package com.clike.sample.springdata.rest.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.util.Assert;

@Entity
@Table(name = "Customer")
public class Customer extends AbstractEntity {

  private String firstname, lastname;

  @Column(unique = true)
  private EmailAddress emailAddress;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "customer_id")
  private Set<Address> addresses = new HashSet<Address>();

  @ManyToOne
  @JoinColumn(name = "role_id")
  private Role role;

  public Customer(String firstname, String lastname) {

    Assert.hasText(firstname);
    Assert.hasText(lastname);

    this.firstname = firstname;
    this.lastname = lastname;
  }

  protected Customer() {

  }

  public void add(Address address) {

    Assert.notNull(address);
    this.addresses.add(address);
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public EmailAddress getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(EmailAddress emailAddress) {
    this.emailAddress = emailAddress;
  }

  public Set<Address> getAddresses() {
    return Collections.unmodifiableSet(addresses);
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }



}
