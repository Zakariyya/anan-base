package anan.base.rbac.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author anan
 * @created 2019/1/28 15:43
 */
@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade  {

  @Override
  public Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }
}
