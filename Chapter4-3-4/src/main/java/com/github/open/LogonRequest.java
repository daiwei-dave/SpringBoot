package com.github.open;



import com.rop.AbstractRopRequest;
import com.rop.annotation.IgnoreSign;

/**
 * @Description
 * @Author zhangliewei
 * @Date 2017/2/9
 * @Copyright(c) gome inc Gome Co.,LTD
 */

public class LogonRequest extends AbstractRopRequest {

//    @Pattern(regexp = "\\w{4,30}")
    private String userName;

    @IgnoreSign
//    @Pattern(regexp = "\\w{6,30}")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
