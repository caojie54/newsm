package com.cj.newsm.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.cj.newsm.model.NewsInfo;
import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;



@Service
public class NewsInfoService {
	private static final String TABLENAME="tb_news_info";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 列出新闻信息
	 * 
	 * 
	 * @param title
	 * 
	 */
	public List<NewsInfo> list(String title){
		String whereSQL="";
		
		if(null!=title && !title.trim().isEmpty()){
			whereSQL =" WHERE title LIKE '%"+title+"%'";
		}
		
		List<NewsInfo> newlist=jdbcTemplate.query("select * from "+TABLENAME+whereSQL,new RowMapper<NewsInfo>(){
			public NewsInfo mapRow(ResultSet resultSet,int rowNum) throws SQLException{
				NewsInfo news =new NewsInfo();
				news.setId(resultSet.getInt("id"));
				news.setTitle(resultSet.getString("title"));
				news.setAuthor(resultSet.getString("author"));
				news.setSource(resultSet.getString("source"));
				news.setCategory_id(resultSet.getInt("category_id"));
				news.setDisplay_time(resultSet.getDate("display_time"));
				return news;
			}
		});
		return newlist;
	}
	
	/**
	 * 保存信息到数据库
	 * @param newsInfo
	 * @return
	 */
	public int save(NewsInfo newsInfo){
		System.out.println(newsInfo);
		String sql="insert into "+TABLENAME+" values(null,'"+newsInfo.getTitle()+"','"+
				newsInfo.getContent()+"',"+newsInfo.getCategory_id()+",'2015-12-25','"+newsInfo.getAuthor()+"','"+newsInfo.getSource()
				+"',"+"null,null"+")";
		int executeResult= jdbcTemplate.update(sql);
		
		return executeResult;
	}
	
	/**
	 * 删除信息
	 * 
	 * 
	 */
	public void delete(int id){
		int excuRes=jdbcTemplate.update("delete from "+TABLENAME+" where id="+id);
		System.out.println("delete return num"+excuRes);
		
	}
	/**
	 * 修改新闻信息
	 * 
	 * 
	 */
	public int modify(NewsInfo newsInfo){
		int excuRes=jdbcTemplate.update("update "+TABLENAME+" set title='"+newsInfo.getTitle()+"',content='"+newsInfo.getContent()+"',author='"+newsInfo.getAuthor()+"',source='"+newsInfo.getSource()+"' where id="+newsInfo.getId());
	    System.out.println("modify return num "+excuRes);
	    return excuRes;
	}
	/**
	 * 获取新闻信息byid
	 * 
	 */
	public NewsInfo getbyid(Integer id){
		
		List<NewsInfo> newlist=jdbcTemplate.query("select * from "+TABLENAME+" where id="+id,new RowMapper<NewsInfo>(){
			public NewsInfo mapRow(ResultSet resultSet,int rowNum) throws SQLException{
				NewsInfo news =new NewsInfo();
				news.setId(resultSet.getInt("id"));
				news.setTitle(resultSet.getString("title"));
				news.setAuthor(resultSet.getString("author"));
				news.setSource(resultSet.getString("source"));
				news.setContent(resultSet.getString("content"));
				return news;
			}
		});
		return newlist.get(0);
	}

}
