package com.anan.sb.springboot.filemanage.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



//@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

//    @Bean
//    public FilterRegistrationBean filterRegistrationCorsBean() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        CorsFilter corsFilter = new CorsFilter();
//        filterRegistrationBean.setFilter(corsFilter);
//        filterRegistrationBean.setEnabled(true);
//        filterRegistrationBean.addInitParameter("allowOrigin", "*");
//        filterRegistrationBean.addInitParameter("allowMethods", "GET,POST,PUT,DELETE,OPTIONS");
//        filterRegistrationBean.addInitParameter("allowCredentials", "true");
//        filterRegistrationBean.addInitParameter("allowHeaders", "Content-Type,X-Token,X-Username,x-auth-token,x-auth-appkey,x-auth-appname");
//        filterRegistrationBean.addInitParameter("Access-Control-Allow-Origin", "*");
//        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.setOrder(0);
//        return filterRegistrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean filterRegistrationWebContextBean() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        WebContextFilter webContextFilter = new WebContextFilter();
//        filterRegistrationBean.setFilter(webContextFilter);
//        filterRegistrationBean.setOrder(0);
//        filterRegistrationBean.addUrlPatterns("/*");
//        return filterRegistrationBean;
//    }

	
}
