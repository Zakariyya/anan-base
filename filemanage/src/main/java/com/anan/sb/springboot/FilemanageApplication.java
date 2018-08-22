package com.anan.sb.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScan({"com.anan.springboot.core","com.anan.sb.springboot"})
public class FilemanageApplication {
  public static void main(String[] args) {
    SpringApplication.run(FilemanageApplication.class, args);
  }
}
