package com.dw.web.config.shiro;


import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
* @Description shiro
* @Author zhangliewei
* @Date 2017/3/28
* @Copyright(c) gome inc Gome Co.,LTD
*/
public class ShiroAuthorizeReam extends AuthorizingRealm  {

//    @Autowired
//    private UserService userService;

    /**
     * 权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        User user = (User) principalCollection.fromRealm(getName()).iterator().next();
//        if (user!=null) {
//            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//            info.addRole("admin");
//            info.addStringPermission("admin:*");
//            return info;
//        }
        return null;
    }

//    @Override
//    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        try {
//            this.userService = (UserService) SpringUtil.getBean("userService");
//        } catch (Exception e) {
//            System.out.println("Error loading: " + e.getMessage());
//            throw new Error("Critical system error", e);
//        }
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent servletContextEvent) {
//
//    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String password = String.valueOf(token.getPassword());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token.getUsername(), encryptPassword(password),null,getName());
        return info;
    }

    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
        String credentials = "Abcd1234";
        int hashIterations = 1;
        Object obj = new SimpleHash(hashAlgorithmName, credentials, null, hashIterations);
        System.out.println(obj);
    }

    Object encryptPassword(String password){
        String hashAlgorithmName = "MD5";
        String credentials = password;
        int hashIterations = 1;
        Object obj = new SimpleHash(hashAlgorithmName, credentials, null, hashIterations);
        return obj;
    }
}
