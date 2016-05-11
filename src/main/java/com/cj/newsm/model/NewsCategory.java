package com.cj.newsm.model;

import java.util.Date;

import com.mysql.jdbc.interceptors.ServerStatusDiffInterceptor;

public class NewsCategory {
	private Integer id;
	private Integer pid;
	private String name;
	private Date create_time;
	private Date modify_time;
	
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public  Integer getPid(){
		return pid;
	}
	public void setPid(Integer pid){
		this.pid=pid;
	}
	public String getName(){
		return name;
	}
    public void setName(String name){
    	this.name=name;
    }
    public Date getCreateTime(){
    	return create_time;
    }
    public void setCreateTime(Date date){
    	this.create_time=date;
    }
    public Date getModifyTime(){
    	return modify_time;
    }
    public void setModifyTime(Date date){
    	this.modify_time=date;
    }

}
