package com.cj.newsm.controller;

import java.util.List;

import javax.websocket.server.PathParam;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.beetl.ext.tag.DeleteTag;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cj.newsm.model.MailSenderModel;
import com.cj.newsm.model.NewsInfo;
import com.cj.newsm.model.SimpleMailSender;
import com.cj.newsm.model.newsinfoMapper;
import com.cj.newsm.service.NewsCategoryService;
import com.cj.newsm.service.NewsInfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.glass.ui.View;
@Controller
@RequestMapping("/newsinfo")
public class NewsInfoController {

	private static final Logger log = LogManager.getLogger(NewsInfoController.class);
	@Autowired
	private NewsInfoService newsInfoService;
	@Autowired
	private newsinfoMapper newsinfoMapper;
    //邮件发送模板
	MailSenderModel m = new MailSenderModel();
	/**
	 * 列出新闻信息
	 * 
	 * @param title
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView listPage(@RequestParam("t") String title) {

		if (log.isDebugEnabled()) {
			log.info("访问了list页面");
		}
		//发送邮件
		boolean b=SimpleMailSender.sendHtmlMail(m);
		System.out.println(b);
		
		ModelAndView view = new ModelAndView("/newsinfo/list.html");
		

		// 获取第1页，10条内容，默认查询总数count
		PageHelper.startPage(1, 5);

		// 通过title搜索news
		if (title == null || title.trim().isEmpty()) {
			List<NewsInfo> list = newsinfoMapper.getNewsAll();
			view.addObject("news", list);
		} else {
			//匹配%title%
			String title1='%'+title+'%';
			List<NewsInfo> list = newsinfoMapper.getNewsByT(title1);
			view.addObject("news", list);
		}
		

		return view;
	}
	/**
	 * 返回json数据的新闻信息
	 * 
	 */
	@ResponseBody
	@RequestMapping(value="/newsjson",produces="application/json;charset=utf-8")
	public String  getJson(@RequestParam("limit")Integer limit,@RequestParam("offset")Integer offset,@RequestParam(value="search",required=false)String title){
		// 获取第1页，10条内容，默认查询总数count
				PageHelper.startPage(offset/limit+1, limit);

				// 通过title搜索news
				if (title == null || title.trim().isEmpty()) {
					List<NewsInfo> list = newsinfoMapper.getNewsAll();
					//将list转换为json数据格式
					JSONArray jsonArray=new JSONArray(list);
					//用PageInfo对结果进行包装
					PageInfo page = new PageInfo(list);
					String json="{ \"total\":"+page.getTotal()+",\"rows\":"+jsonArray.toString()+"}";
					System.out.println(json);
					return json;
				} else {
					//匹配%title%
					String title1='%'+title+'%';
					List<NewsInfo> list = newsinfoMapper.getNewsByT(title1);
					//将list转换为json数据格式
					JSONArray jsonArray=new JSONArray(list);
					//用pageinfo对结果进行包装
					PageInfo page =new PageInfo(list);
					//包装好返回到前台的json数据（bootstraptable要求的格式）
					String json="{ \"total\":"+page.getTotal()+",\"rows\":"+jsonArray.toString()+"}";
					return json;
				}
		
	}
	

	/**
	 * 添加新闻的页面
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	public ModelAndView addPage() {
		ModelAndView view = new ModelAndView("/newsinfo/add.html");
		return view;
	}

	/**
	 * 保存新闻
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(NewsInfo newsinfo) {
		if (log.isInfoEnabled()) {
			log.info("添加了一条新闻信息");
		}
		int ss = newsInfoService.save(newsinfo);
		System.out.println(ss);
		return "ok";

	}

	/**
	 * 修改新闻的页面
	 * 
	 */
	@RequestMapping("/modify")
	public ModelAndView modifyPage(@RequestParam("id") Integer id) {

		ModelAndView view = new ModelAndView("/newsinfo/modify.html");
		NewsInfo newsInfo = newsInfoService.getbyid(id);

		view.addObject("newsinfo", newsInfo);
		return view;
	}

	/**
	 * 修改新闻
	 */
	@ResponseBody
	@RequestMapping(value = "/modifyNews", method = RequestMethod.POST)
	public String modify(NewsInfo newsInfo) {
		if (log.isInfoEnabled()) {
			log.info("修改了一条新闻信息");
		}
		int ss = newsInfoService.modify(newsInfo);
		System.out.println("修改的页面：" + ss);
		return "ok";
	}

	/**
	 * 删除新闻
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteNews", method = RequestMethod.POST)
	public String delete(@RequestParam("did") int id) {

		if (log.isInfoEnabled()) {
			log.info("删除了一条新闻信息");
		}
		newsInfoService.delete(id);
		return "ok";
	}
	
	
	

}
