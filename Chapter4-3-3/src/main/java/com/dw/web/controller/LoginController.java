package com.dw.web.controller;

import com.dw.web.common.ErrorCode;
import com.dw.web.common.ResultData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
* @Description
* @Author daiwei
* @Date 2017/9/18
* @Copyright(c) gome inc Gome Co.,LTD
*/
@Controller
public class LoginController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("message", "hello web");
        return modelAndView;
    }
    


    @RequestMapping("/main")
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        modelAndView.addObject("message", "主页");
        return modelAndView;
    }



    /**
     * 登录
     * 先校验验证码，后校验账户名和密码
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultData login(HttpServletRequest httpServletRequest, String username, String password, Boolean rememberMe) {
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            logger.error("参数为空：{},{}", username,password);
            return ResultData.newResultData(ErrorCode.FAILOR, "参数为空");
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        if (rememberMe==null){
            token.setRememberMe(false);
        }else{
            token.setRememberMe(rememberMe);
        }
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
            //验证是否登录成功
            if (currentUser.isAuthenticated()) {
                return ResultData.newSuccessResultData();
            }
        } catch (UnknownAccountException uae) {
            logger.error("user[" + username + "]UnknownAccountException");
            return ResultData.newResultData(ErrorCode.FAILOR,"该账号不存在，请重新输入");
        }catch (LockedAccountException lae) {
            logger.error("user[" + username + "]LockedAccountException");
            return ResultData.newResultData(ErrorCode.FAILOR,"账户锁定");
        }catch (DisabledAccountException uae) {
            logger.error("user[" + username + "]DisabledAccountException");
            return ResultData.newResultData(ErrorCode.FAILOR,"账户被禁用");
        } catch (IncorrectCredentialsException ice) {
            logger.error("user[" +username + "]IncorrectCredentialsException");
            return ResultData.newResultData(ErrorCode.FAILOR,"密码错误，请重新输入");
        }  catch (ExcessiveAttemptsException eae) {
            logger.error("user[" + username + "]ExcessiveAttemptsException");
            return ResultData.newResultData(ErrorCode.FAILOR,"重试次数过多，请稍后重试");
        } catch (AuthenticationException ae) {
            logger.error("user[" + username + "]AuthenticationException");
            return ResultData.newResultData(ErrorCode.FAILOR,"认证失败");
        }
        return ResultData.newResultData(ErrorCode.FAILOR,"登录失败！");
    }
    
    
    /**
     * 退出登录
     * @return
     */
    @RequestMapping(value = "/logout",produces="application/json;charset=UTF-8")
    @ResponseBody
    public ResultData<Object> logout() {
        Subject subject = SecurityUtils.getSubject();  
        
        if (subject != null) {  
            try{  
                subject.logout(); 
                logger.debug("用户退出系统");
                return ResultData.newSuccessResultData();
            }catch(Exception ex){  
                logger.error("用户退出系统异常");
                return ResultData.newResultData(ErrorCode.FAILOR,ErrorCode.FAILOR_MSG);
            }  
        }  
        logger.debug("用户退出系统");
        return ResultData.newSuccessResultData();
       
    }

    /**
     * 查询session是否过期
     * @return
     */
    @RequestMapping(value = "/checkSession")
    @ResponseBody
    public ResultData checkSession() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(false);
        if (session==null){
            logger.error("session已经过期");
            return ResultData.newResultData(ErrorCode.FAILOR,"session已经过期");
        }
        return ResultData.newSuccessResultData();
    }
}
