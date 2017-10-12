package com.dw.web.config.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
* @Description  shiro配置
* @Author daiwei
* @Date 2017/3/28
* @Copyright(c)
*/
@Configuration
public class ShiroConfiguration {

    /**
     * Shiro生命周期处理器
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 凭证匹配器
     * 这里设置了相应加密算法后，在向SimpleAuthenticationInfo传入的hashedCredentials也要以相同的方式加密
     * @return
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(1);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }


    @Bean(name = "shiroAuthorizeReam")
    @DependsOn("lifecycleBeanPostProcessor")
    public ShiroAuthorizeReam shiroAuthorizeReam() {
        ShiroAuthorizeReam realm = new ShiroAuthorizeReam();
  //      realm.setCacheManager(cacheManager());
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }


//    @Bean(name = "cacheManager")
//    public RedisCacheManager cacheManager() {
//        RedisCacheManager cacheManager = new RedisCacheManager();
//        return cacheManager;
//    }

//    @Bean
//    public RedisSessionRepository sessionRepository() {
//        RedisSessionRepository sessionRepository = new RedisSessionRepository();
//        return sessionRepository;
//    }


    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(1800000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationInterval(1800000);
  //      sessionManager.setSessionDAO(sessionRepository());
        sessionManager.setSessionValidationSchedulerEnabled(true);
        return sessionManager;
    }


    /**
     * cookie对象;
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        //cookie有效期30天
        cookie.setMaxAge(2592000);
        cookie.setHttpOnly(true);
        return cookie;
    }

    @Bean(name = "securityManager")
    public org.apache.shiro.mgt.SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //注入自定义Realm
        securityManager.setRealm(shiroAuthorizeReam());
        //注入缓存管理器
  //      securityManager.setCacheManager(cacheManager());
        //注入session管理器
        securityManager.setSessionManager(sessionManager());
        //注入记住我管理器;
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager());
        return new AuthorizationAttributeSourceAdvisor();
    }

    /**
     * cookie管理对象
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
  //      cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }

    @Bean
    SessionExpiredFilter sessionExpiredFilter(){
        SessionExpiredFilter sessionExpiredFilter=new SessionExpiredFilter();
        return sessionExpiredFilter;
    }

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     *
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
//        LogoutFilter logoutFilter = new LogoutFilter();
//        logoutFilter.setRedirectUrl("/index.html");
        filters.put("user", new SessionExpiredFilter());
//        filters.put("logout", new com.gome.performance.web.config.shiro.LogoutFilter());
        shiroFilterFactoryBean.setFilters(filters);

        // 拦截器.拦截器是按照设置的顺序的优先级执行的
        Map<String, String> filterChainDefinitionManager = new LinkedHashMap<String, String>();
        filterChainDefinitionManager.put("/index.html","anon");
        filterChainDefinitionManager.put("/static/**","anon");
        filterChainDefinitionManager.put("/js/**","anon");
        filterChainDefinitionManager.put("/login","anon");
        filterChainDefinitionManager.put("/logout","anon");
        //访问该地址的用户是身份验证通过或RememberMe登录的都可以，
        filterChainDefinitionManager.put("/**", "user");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);

        shiroFilterFactoryBean.setLoginUrl("/index.html");
        shiroFilterFactoryBean.setSuccessUrl("/main.html");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        return shiroFilterFactoryBean;
    }

}

