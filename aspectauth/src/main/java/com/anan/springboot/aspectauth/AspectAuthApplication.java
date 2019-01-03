package com.anan.springboot.aspectauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"anan.base.core.,"com.anan.springboot.aspectauth"})
public class AspectAuthApplication {

  public static void main(String[] args) {
    SpringApplication.run(AspectAuthApplication.class, args);
  }
}
