package com.clike.sample.springdata.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
@EntityScan(basePackages = "com.clike.sample.springdata.rest.*")
@EnableJpaRepositories(basePackages = "com.clike.sample.springdata.rest.*")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
