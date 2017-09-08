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
    private List<Map<String, String>> listProp1 = new ArrayList<>(); //接收prop1里面的属性值
    private Map<String, String> mapProps = new HashMap<>(); //接收prop1里面的属性值
    private List<String> listProp2 = new ArrayList<>(); //接收prop2里面的属性值

    public List<String> getListProp2() {
        return listProp2;
    }

    public void setListProp2(List<String> listProp2) {
        this.listProp2 = listProp2;
    }

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

    public Map<String, String> getMapProps() {
        return mapProps;
    }

    public void setMapProps(Map<String, String> mapProps) {
        this.mapProps = mapProps;
    }
    public List<Map<String, String>> getListProp1() {
        return listProp1;
    }

    public void setListProp1(List<Map<String, String>> listProp1) {
        this.listProp1 = listProp1;
    }



}
