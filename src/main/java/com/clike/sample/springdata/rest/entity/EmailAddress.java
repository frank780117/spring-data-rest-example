package com.clike.sample.springdata.rest.entity;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Embeddable
@JsonSerialize(using = ToStringSerializer.class)
public class EmailAddress {

  private static final String EMAIL_REGEX =
      "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
  private static final Pattern PATTERN = Pattern.compile(EMAIL_REGEX);

  @Column(name = "email")
  private String value;

  public EmailAddress(String emailAddress) {
    Assert.isTrue(isValid(emailAddress), "Invalid email address!");
    this.value = emailAddress;
  }

  protected EmailAddress() {

  }

  public static boolean isValid(String source) {
    return PATTERN.matcher(source).matches();
  }

  @Override
  public String toString() {
    return value;
  }
}
