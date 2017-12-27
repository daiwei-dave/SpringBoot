package com.didispace.entity;


import java.io.Serializable;

/**
 * 权限表
 * @Description
 * @Author daiwei
 * @Date 2017/12/14
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class Postion implements Serializable{

	private static final long serialVersionUID = 1L;	
	
	private String postNo;//   岗位代码
	private String postName;//   岗位名称
	private Integer level;//   等级；0为总部，1为非总部

	public Postion(String postNo) {
		this.postNo = postNo;
	}

	public Postion() {

	}

	public String getPostNo() {
	    return this.postNo;
	}

	public void setPostNo(String postNo) {
	    this.postNo = postNo;
	}

	public String getPostName() {
	    return this.postName;
	}

	public void setPostName(String postName) {
	    this.postName = postName;
	}

	public Integer getLevel() {
	    return this.level;
	}

	public void setLevel(Integer level) {
	    this.level = level;
	}

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Postion [");   
                        builder.append("postNo=");
                builder.append(postNo);
                        builder.append(", postName=");
                builder.append(postName);
                        builder.append(", level=");
                builder.append(level);
                builder.append("]");
        return builder.toString();
    }
}

