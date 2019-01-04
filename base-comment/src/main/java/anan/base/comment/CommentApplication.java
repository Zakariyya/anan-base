package anan.base.comment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"anan.base.core","anan.base.comment"})
public class CommentApplication {

  public static void main(String[] args) {
    SpringApplication.run(CommentApplication.class, args);
  }
}
