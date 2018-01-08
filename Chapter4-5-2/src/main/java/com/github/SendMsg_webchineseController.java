package com.github;

import com.gitee.common.constants.ErrorCode;
import com.gitee.common.entity.ResultData;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by daiwei on 2018/1/8.
 */
@Controller
@RequestMapping("/push")
public class SendMsg_webchineseController {


    /**
     * @author hang
     * @Decription 注册，发送短信验证码,保存到Session中
     * @return 返回状态参数
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/pushMessage", method = RequestMethod.POST)
    public ResultData SendCheckMessage(HttpServletRequest request,UserBean u)
            throws Exception {
        String message = "发送成功";
        String phone=u.getTelephone();   //获取到客户端发来的手机号
        UserBean user = getByPhone(phone);
        if (user != null) {
            message = "该手机号已被注册";
            return ResultData.newResultData(ErrorCode.FAILOR,message);
        } else {
            HashMap<String, String> m = SendMsg_webchineseController.getMessageStatus(phone);  //应用发送短信接口
            String result = m.get("result");              //获取到result值
            if (result.trim().equals("1")) {             //如果为1，表示成功发送
                String code = m.get("code");           //获取发送的验证码内容
     //           logger.info("发送的验证码:"+code);     //打印日志
                HttpSession session = request.getSession(); //设置session
                session.setAttribute("code", code);             //将短信验证码放到session中保存
                session.setMaxInactiveInterval(60 * 5);//保存时间 暂时设定为5分钟
                return ResultData.newResultData(ErrorCode.SUCCESS,message);
            } else {
                message = "短信发送失败";
                return ResultData.newResultData(ErrorCode.FAILOR,message);
            }
        }
    }

    private UserBean getByPhone(String phone) {
        UserBean userBean=new UserBean();
        userBean.setTelephone(phone);
        return null;
    }

    public static HashMap<String,String> getMessageStatus(String phone) throws IOException {
        HashMap<String,String> m=new HashMap<String,String>();
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
        String code="123456";
        NameValuePair[] data ={ new NameValuePair("Uid", "daiwei2018"),new NameValuePair("Key", "17f1f926a0a5f4cdfa6b"),new NameValuePair("smsMob",phone),new NameValuePair("smsText","您正在注册本站会员,本次验证码为:"+code+""+"有效时间为5分钟")};
        m.put("code", code);
        post.setRequestBody(data);

        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:"+statusCode);
        for(Header h : headers)
        {
            System.out.println(h.toString());
        }
        String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
        System.out.println(result); //打印返回消息状态
        m.put("result", result);
        post.releaseConnection();
        return m;
    }
}
