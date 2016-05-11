package com.cj.newsm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cj.newsm.model.NewsCategory;
import com.cj.newsm.service.NewsCategoryService;
import com.cj.newsm.service.NewsInfoService;

@Controller
@RequestMapping("/newscategory")
public class NewsCategoryController {
	
	@Autowired
	private NewsCategoryService newsCategoryService;
	@Autowired
	private NewsInfoService newsInfoService;
	
	/**
	 * 列出新闻分类信息列表
	 * 
	 */
	@RequestMapping("/list")
	public ModelAndView listPage(){
		ModelAndView view=new ModelAndView("/newscategory/categorylist.html");
		//绑定list category
		view.addObject("categorys",newsCategoryService.list());
		//绑定list newsinfo
		String title="";
		view.addObject("newslist",newsInfoService.list(title));
	
		return view;
	};
	
	/**
	 * 删除新闻分类
	 */
	@ResponseBody
	@RequestMapping(value="/removeCate",method=RequestMethod.POST)
	public  String removeCate(@RequestParam("rid")Integer id){
		newsCategoryService.deleteCate(id);
		return "ok";
	}
	
	/**
	 * 更改新闻分类名称
	 */
	@ResponseBody
	@RequestMapping(value="/renameCate",method=RequestMethod.POST)
	public String renameCate(@RequestParam("rid")Integer id,@RequestParam("rname")String name){
		int re=newsCategoryService.renameCate(id,name);
		if(re == 1){
			return "ok";
		}else{
			return "";
		}
	}
	
	/**
	 * 新增新闻分类
	 * 
	 */
	@ResponseBody
	@RequestMapping(value="/addCate",method=RequestMethod.POST)
	public String addCate(NewsCategory newsCategory){
		int re=newsCategoryService.addCate(newsCategory);
		if(re == 1){
			return "ok";
		}else{
			return "";
		}
		
		
		
	}

}
