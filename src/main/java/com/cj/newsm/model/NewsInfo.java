package com.cj.newsm.model;

import java.util.Date;

public class NewsInfo {
	//���ű��
	private Integer id;
	//���ű���
	private String title;
	//��������
	private String content;
	//��������
	private String author;
	//������������
	private Integer category_id;
	//������Դ
	private String source;
	//������ʾʱ��
	private Date display_time;
	//���Ŵ���ʱ��
	private Date create_time;
	//�����޸�ʱ��
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
