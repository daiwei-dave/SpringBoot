package com.didispace.entity;

import java.io.Serializable;

/**
 * @author 程序猿DD
 * @version 1.0.0
 * @date 16/4/15 下午1:58.
 * @blog http://blog.didispace.com
 */
public class User implements Serializable {

    private static final long serialVersionUID = -1L;

    private String userName;
    private Integer age;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String password;



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }



}
