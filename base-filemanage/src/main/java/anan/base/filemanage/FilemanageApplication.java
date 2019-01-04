package anan.base.filemanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"anan.base.core","anan.base.filemanage"})
public class FilemanageApplication {
  public static void main(String[] args) {
    SpringApplication.run(FilemanageApplication.class, args);
  }
}
