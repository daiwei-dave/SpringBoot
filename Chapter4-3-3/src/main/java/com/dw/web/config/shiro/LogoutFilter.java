package com.dw.web.config.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by zhangliewei on 2017/7/5.
 */
public class LogoutFilter extends org.apache.shiro.web.filter.authc.LogoutFilter {

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {

        return super.preHandle(request, response);
    }
}
