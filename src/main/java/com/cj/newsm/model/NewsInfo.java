package com.cj.newsm.model;

import java.util.Date;

public class NewsInfo {
	//新闻编号
	private Integer id;
	//新闻标题
	private String title;
	//新闻内容
	private String content;
	//新闻作者
	private String author;
	//新闻所属分类
	private Integer category_id;
	//新闻来源
	private String source;
	//新闻显示时间
	private Date display_time;
	//新闻创建时间
	private Date create_time;
	//新闻修改时间
	private Date modify_time;
	
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content=content;
	}
	public String getAuthor(){
		return author;
	}
	public void setAuthor(String author){
		this.author=author;
	}
	public String getSource(){
		return source;
	}
	public void setSource(String source){
		this.source=source;
	}
	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id =category_id;
	}

	public Date getDisplay_time() {
		return display_time;
	}

	public void setDisplay_time(Date display_time) {
		this.display_time = display_time;
	}
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getModify_time() {
		return modify_time;
	}

	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
    @Override
    public String toString(){
    	return "NewsInfo [id="+id+",title="+title+",content="+content+",category_id="+category_id+",display_time="+",author"+author+",source="+source+",create_time="+create_time+",modify_time="+modify_time+"]";
    }

	

}
