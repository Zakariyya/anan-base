package anan.base.auth.config;

import anan.base.auth.orm.User;
import anan.base.auth.repository.UserRepository;
import anan.base.auth.security.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author anan
 * Created on 2018/8/27.
 *
 * 简要说明：
 *
 * 1.通过 @EnableWebSecurity注解开启Spring Security的功能。
 *   使用@EnableGlobalMethodSecurity(prePostEnabled = true)这个注解，
 *   可以开启security的注解，我们可以在需要控制权限的方法上面使用@PreAuthorize，@PreFilter这些注解。
 *
 * 2.extends 继承 WebSecurityConfigurerAdapter 类，并重写它的方法来设置一些web安全的细节。
 *   我们结合@EnableWebSecurity注解和继承WebSecurityConfigurerAdapter，来给我们的系统加上基于web的安全机制。
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception { //配置策略
//    super.configure(http);
//    http
//            .authorizeRequests()
//            .anyRequest().authenticated()
//            .and()
//            .formLogin()
//            //设置登陆页面
//            .loginPage("/user/login")
//            //允许所有人进行访问此路径
//            .permitAll();
    //关闭csrf保护
//                    .and().csrf().disable();
    http.csrf().disable();
    //logoutSuccessUrl("/login")//使用了logoutSuccessHandler实现了这个功能
    http.authorizeRequests().
            antMatchers("/static/**").permitAll().anyRequest().authenticated().
            and().formLogin().loginPage("/login").permitAll().successHandler(loginSuccessHandler()).
            and().logout().permitAll().invalidateHttpSession(true).
            deleteCookies("JSESSIONID").logoutSuccessHandler(logoutSuccessHandler()).
            and().sessionManagement().maximumSessions(10).expiredUrl("/login");
  }

//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth
//      .inMemoryAuthentication()
//      .withUser("root").password("{noop}root").roles("USER")
//      .and()
//      .withUser("admin").password("{noop}admin").roles("ADMIN", "USER")
//      .and()
//      .withUser("user").password("{noop}user").roles("USER");
//
//  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    auth.eraseCredentials(false);
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() { //密码加密
    return new BCryptPasswordEncoder(4);
  }

  @Bean
  public SavedRequestAwareAuthenticationSuccessHandler loginSuccessHandler() { //登入处理
    return new SavedRequestAwareAuthenticationSuccessHandler() {
      @Override
      public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User userDetails = (User) authentication.getPrincipal();
        logger.info("USER : " + userDetails.getAccount() + " LOGIN SUCCESS !  ");
        super.onAuthenticationSuccess(request, response, authentication);
      }
    };
  }


  @Bean
  public LogoutSuccessHandler logoutSuccessHandler() { //登出处理
    return new LogoutSuccessHandler() {
      @Override
      public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        try {
          SecurityUser user = (SecurityUser) authentication.getPrincipal();
          log.info("USER : " + user.getUsername() + " LOGOUT SUCCESS !  ");
        } catch (Exception e) {
          log.info("LOGOUT EXCEPTION , e : " + e.getMessage());
        }
        httpServletResponse.sendRedirect("/login");
      }
    };
  }

  @Bean
  public UserDetailsService userDetailsService() {    //用户登录实现
    return new UserDetailsService() {
      @Autowired
      private UserRepository userRepository;

      @Override
      public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByAccount(s).get(0);
        if (user == null) throw new UsernameNotFoundException("Username " + s + " not found");
        return new SecurityUser(user);
      }
    };
  }







}
