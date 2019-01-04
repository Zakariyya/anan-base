package anan.base.content;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"anan.base.core","anan.base.content"})
public class ContentApplication {

  public static void main(String[] args) {
    SpringApplication.run(ContentApplication.class, args);
  }
}
