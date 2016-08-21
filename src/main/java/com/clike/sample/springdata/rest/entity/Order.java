/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.clike.sample.springdata.rest.entity;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.AccessType.Type;
import org.springframework.util.Assert;

@Entity
@Table(name = "Orders")
public class Order extends AbstractEntity {

  @ManyToOne(optional = false)
  private Customer customer;

  @OneToOne(cascade = CascadeType.ALL)
  private Address billingAddress;

  @OneToOne(optional = false, cascade = CascadeType.ALL)
  @JoinColumn(name = "SHIPPINGADDRESS_ID")
  private Address shippingAddress;

  @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval= true)
  @JoinColumn(name = "order_id")
  @AccessType(Type.PROPERTY)
  private Set<LineItem> lineItems = new HashSet<LineItem>();

  public Order(Customer customer, Address shippingAddress) {
    this(customer, shippingAddress, null);
  }

  public Order(Customer customer, Address shippingAddress, Address billingAddress) {

    Assert.notNull(customer);
    Assert.notNull(shippingAddress);

    this.customer = customer;
    this.shippingAddress = shippingAddress.getCopy();
    this.billingAddress = billingAddress == null ? null : billingAddress.getCopy();
  }

  protected Order() {

  }

  public void add(LineItem lineItem) {
    this.lineItems.add(lineItem);
  }

  public Customer getCustomer() {
    return customer;
  }

  public Address getBillingAddress() {
    return billingAddress != null ? billingAddress : shippingAddress;
  }

  public Address getShippingAddress() {
    return shippingAddress;
  }

  public Set<LineItem> getLineItems() {
    return Collections.unmodifiableSet(lineItems);
  }

  public void setLineItems(Set<LineItem> lineItems) {
    this.lineItems.clear();
    this.lineItems.addAll(lineItems);
  }

  public BigDecimal getTotal() {

    BigDecimal total = BigDecimal.ZERO;

    for (LineItem item : lineItems) {
      total = total.add(item.getTotal());
    }

    return total;
  }
}
