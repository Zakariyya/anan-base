package com.anan.springboot.content;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.anan.springboot.core","com.anan.springboot.content"})
public class ContentApplication {

  public static void main(String[] args) {
    SpringApplication.run(ContentApplication.class, args);
  }
}
