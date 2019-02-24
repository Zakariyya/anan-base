package anan.base.rbac.security;

import org.springframework.security.core.Authentication;

/**
 * @author anan
 * Created on 2019.01.28
 */
public interface AuthenticationFacade {
  Authentication getAuthentication();
}
