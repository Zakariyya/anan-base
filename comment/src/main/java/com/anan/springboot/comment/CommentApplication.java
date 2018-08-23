package com.anan.springboot.comment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.anan.springboot.core","com.anan.springboot.comment"})
public class CommentApplication {

  public static void main(String[] args) {
    SpringApplication.run(CommentApplication.class, args);
  }
}
