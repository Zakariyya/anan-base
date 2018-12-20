package com.anan.springboot.authshiro.authshiro.shiro;

import com.urundp.desktop.vda.auth.service.UserService;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@ServletComponentScan
public class ShiroConfig {

	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Autowired
	@Bean(name = "shiroRealm")
	@DependsOn({ "lifecycleBeanPostProcessor" })
	public ShiroRealm shiroRealm(UserService userService) {
		ShiroRealm realm = new ShiroRealm();
		realm.setUserService(userService);
		return realm;
	}

	@Bean(name = "ehCacheManager")
	@DependsOn("lifecycleBeanPostProcessor")
	public EhCacheManager ehCacheManager() {
		EhCacheManager em = new EhCacheManager();
		em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
		return em;
	}

	@Autowired
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager securityManager(ShiroRealm shiroRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(shiroRealm);
		securityManager.setCacheManager(ehCacheManager());
		return securityManager;
	}

	@Autowired
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		// 验证码拦截器
		shiroFilterFactoryBean.getFilters().put("validateCodeCheckFilter", new ValidateCodeCheckFilter());

		shiroFilterFactoryBean.setLoginUrl("/auth/login");
		shiroFilterFactoryBean.setSuccessUrl("/");

		Map<String, String> filterChainDefinitionManager = new LinkedHashMap<String, String>();
		
		//static
		filterChainDefinitionManager.put("/bootstrap/**", DefaultFilter.anon.name());
		filterChainDefinitionManager.put("/css/**", DefaultFilter.anon.name());
		filterChainDefinitionManager.put("/fonts/**", DefaultFilter.anon.name());
		filterChainDefinitionManager.put("/img/**", DefaultFilter.anon.name());
		filterChainDefinitionManager.put("/js/**", DefaultFilter.anon.name());
		filterChainDefinitionManager.put("/plugins/**", DefaultFilter.anon.name());
		//login
		filterChainDefinitionManager.put("/auth/logout", DefaultFilter.logout.name());
		filterChainDefinitionManager.put("/auth/login", "validateCodeCheckFilter");
		filterChainDefinitionManager.put("/auth/loginpage", DefaultFilter.anon.name());
		filterChainDefinitionManager.put("/auth/validatecode", DefaultFilter.anon.name());
		//client
		filterChainDefinitionManager.put("/vda/client/**", DefaultFilter.anon.name());
		filterChainDefinitionManager.put("/vda/vm/**", DefaultFilter.anon.name());
		filterChainDefinitionManager.put("/urun/login/**", DefaultFilter.anon.name());
		//web
		filterChainDefinitionManager.put("/web/**", DefaultFilter.anon.name());
		//rpc
		filterChainDefinitionManager.put("/vda/adm/status", DefaultFilter.anon.name());
		//user sign in
		filterChainDefinitionManager.put("/user/signin", DefaultFilter.anon.name());
		
		
		
		//manage
		// filterChainDefinitionManager.put("/vda/**", "roles[ADMIN]");
		// filterChainDefinitionManager.put("/client/**",
		// DefaultFilter.authc.name());
		filterChainDefinitionManager.put("/**", DefaultFilter.authc.name());

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);

		return shiroFilterFactoryBean;
	}

	@Bean
	@ConditionalOnMissingBean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
		defaultAAP.setProxyTargetClass(true);
		return defaultAAP;
	}

	@Autowired
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			DefaultWebSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor aASA = new AuthorizationAttributeSourceAdvisor();
		aASA.setSecurityManager(securityManager);
		return aASA;
	}

}
