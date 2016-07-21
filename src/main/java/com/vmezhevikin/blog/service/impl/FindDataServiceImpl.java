package com.vmezhevikin.blog.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmezhevikin.blog.entity.Article;
import com.vmezhevikin.blog.entity.Author;
import com.vmezhevikin.blog.repository.storage.ArticleRepository;
import com.vmezhevikin.blog.repository.storage.AuthorRepository;
import com.vmezhevikin.blog.service.FindDataService;
import com.vmezhevikin.blog.service.StaticDataService;

@Service
public class FindDataServiceImpl implements FindDataService {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private StaticDataService staticDataService;

	@Override
	public List<Author> findAllAuthors() {
		return authorRepository.findAll();
	}

	@Override
	public List<Article> findAllArticles() {
		return articleRepository.findAll();
	}

	@Override
	public Map<String, Integer> findCategoryStatistic() {
		Map<String, Integer> result = new HashMap<>();
		List<String> categories = staticDataService.findCategoryNames();
		for (String category : categories) {
			int count = articleRepository.countByCategoryName(category);
			result.put(category, count);
		}
		return result;
	}
}