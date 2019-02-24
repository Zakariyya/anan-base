package anan.base.rbac.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.urundp.corona.pub.repo.UserRepository;

import javax.servlet.http.HttpServletRequest;

/**
 * @author anan
 * @created 2019/2/3 15:05
 */

@Component
public class RbacService {


  @Autowired
  protected UserRepository userRepository;

  private AntPathMatcher antPathMatcher = new AntPathMatcher();

  public boolean hasPermission(HttpServletRequest request, Authentication auth) {
    final boolean[] hasPermission = {false};
    UserDetails principal = (UserDetails)auth.getPrincipal();

    // 只有对非匿名用户才有必要进行权限控制
    if (principal instanceof UserDetails) {
      String username = principal.getUsername();

      // 根据username 查询用户所拥有权限的所有URL
//      Set<String> urls = userRepository.findByAccount(username).;
//      // 遍历判断用户所访问的请求是否在其权限允许的范围内
//      urls.forEach(url -> {
//        if (antPathMatcher.match(url, request.getRequestURI())) {
//          hasPermission[0] = true;
//        }
//      });
    }

    return hasPermission[0];
  }
}
