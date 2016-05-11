package com.cj.newsm.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.cj.newsm.model.NewsCategory;

@Service
public class NewsCategoryService {
	private static final String TABLENAME="tb_news_category";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * 
	 * 列出新闻分类列表
	 * 
	 */
	public List<NewsCategory> list(){
		String whereSQL=" ";
		String sql="select * from "+TABLENAME+whereSQL;
		List<NewsCategory> newscatelist=jdbcTemplate.query(sql, new RowMapper<NewsCategory>(){

			public NewsCategory mapRow(ResultSet resultSet, int arg1) throws SQLException {
				
                NewsCategory newscate=new NewsCategory();
                newscate.setId(resultSet.getInt("id"));
                newscate.setPid(resultSet.getInt("pid"));
                newscate.setName(resultSet.getString("category_name"));
                newscate.setCreateTime(resultSet.getDate("create_time"));
                newscate.setModifyTime(resultSet.getDate("modify_time"));
                System.out.println(newscate.getName());
				return newscate;
			}
			
		});
		return newscatelist;
		
	}
	/**
	 * 
	 * 删除新闻分类
	 */
	public void deleteCate(Integer id){
		String sql="delete from "+TABLENAME+" where id="+id;
		int excuteRe=jdbcTemplate.update(sql);
		
	      System.out.println("数据库删除新闻分类结果"+excuteRe);
	}
	/**
	 * 更新新闻分类名称
	 * 
	 */
	public int renameCate(Integer id,String name){
		String sql="update "+TABLENAME+" set category_name='"+name+"' where id="+id;
		int excuteRe=jdbcTemplate.update(sql);
		
		System.out.println("数据库更名结果"+excuteRe);
		return excuteRe;
	}
	
	/**
	 * 增加新闻分类
	 */
	public int addCate(NewsCategory newsCategory){
		String sql="insert into "+TABLENAME+" values("+newsCategory.getId()+","+newsCategory.getPid()+",'"+newsCategory.getName()+"',null,null)";
		int excuteRe=jdbcTemplate.update(sql);
		
		return excuteRe;
	}

}
