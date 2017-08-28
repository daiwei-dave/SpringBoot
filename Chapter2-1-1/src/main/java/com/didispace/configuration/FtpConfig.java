package com.didispace.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取application.yml配置文件
 */
@Component("ftpConfig")
@ConfigurationProperties(prefix = "ftp") //接收application.yml中的myProps下面的属性
public class FtpConfig {
    // 服务器ip
    private String ip;

    // 服务器端口
    private Integer port;

    // 用户名
    private String userName;

    // 用户密码
    private String userPwd;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
}
