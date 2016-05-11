package com.cj.newsm.model;

import java.util.List;

public interface newsinfoMapper {
	public List<NewsInfo> getNewsAll();
	
	public List<NewsInfo> getNewsByT(String title);
}
