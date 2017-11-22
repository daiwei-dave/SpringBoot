package com.didispace.entity;


import com.gitee.excel.userModel.annotations.ExcelField;

/**
 *
 * @author 程序猿DD
 * @version 1.0.0
 * @blog http://blog.didispace.com
 *
 */
public class User {

    private Long id;

    private Integer age;
    @ExcelField(title = "手机号", align = 1, sort = 1,property="phone")
    private String phone;//   手机

    @ExcelField(title = "姓名", align = 1, sort = 2,property="name")
    private String name;//   姓名


    @ExcelField(title = "人员分类", align = 1, sort = 3,property="userGroup")
    private String userGroup;//   1：电器年会  2 ：集团年会  3 ：工作人员


    @ExcelField(title = "备注", align = 1, sort = 4,property="remark")
    private String remark;//   备注

    public User() {
    }

    public User(String phone, String name, String userGroup, String remark) {
        this.phone = phone;
        this.name = name;
        this.userGroup = userGroup;
        this.remark = remark;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", userGroup='" + userGroup + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
