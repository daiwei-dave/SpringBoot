package com.didispace.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by daiwei on 2017/8/28.
 */
@Component("myProps")
@ConfigurationProperties(prefix ="myProps") //接收application.yml中的myProps下面的属性
public class MyProps {
    private String simpleProp;
    private String[] arrayProps;

    public String getSimpleProp() {
        return simpleProp;
    }

    public void setSimpleProp(String simpleProp) {
        this.simpleProp = simpleProp;
    }

    public String[] getArrayProps() {
        return arrayProps;
    }

    public void setArrayProps(String[] arrayProps) {
        this.arrayProps = arrayProps;
    }


}
