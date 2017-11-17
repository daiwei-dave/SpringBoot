package com.github.open;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gome.commons.codec.AES;
import com.rop.client.CompositeResponse;
import com.rop.client.DefaultRopClient;
import com.rop.client.RopClient;
import com.rop.utils.RopUtils;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;




/**
 * Created by daiwei on 2017/11/17.
 */
public class ApiTestTest extends BasicTest{

    private RopClient ropClient = new DefaultRopClient(SERVER_URL, appKey, appSecret);
    @Test
    public void getSession() throws Exception {
        //②构造请求对象
        LogonRequest ropRequest = new LogonRequest();
        ropRequest.setUserName("tomson");
        ropRequest.setPassword("123456");
        //③对服务发起调用并获取响应结果
        CompositeResponse response = ropClient.buildClientRequest().get(ropRequest, LogonResponse.class, "user.getSession", "1.0");

    }


    @Test
    public void sessionTest() throws Exception {
        String method = "demo.sessionTest";
        String params =  "{}";
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
        form.add("method", method);
        form.add("messageFormat", "json");
        form.add("v", "1.0");
        form.add("appKey", appKey);
        form.add("paramsKey", paramsKey);
        params = AES.Encrypt(params, paramsSecret);
        form.add("params", params);
        String sign = RopUtils.sign(params, appSecret);
        form.add("sign", sign);
//        form.add("token","0bfd34e90c784d3d8ee15a6a0a677e6d");
        form.add("sessionId", "0bfd34e90c784d3d8ee15a6a0a677e6d");
        String response = restTemplate.postForObject(SERVER_URL,form,String.class);
        System.out.println(response );
    }


}