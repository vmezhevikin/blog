package com.vmezhevikin.blog.service;

import java.util.List;
import java.util.Map;

import com.vmezhevikin.blog.entity.Article;
import com.vmezhevikin.blog.entity.Author;

public interface FindDataService {
	List<Author> findAllAuthors();
	
	List<Article> findAllArticles();
	
	Map<String, Integer> findCategoryStatistic();
}