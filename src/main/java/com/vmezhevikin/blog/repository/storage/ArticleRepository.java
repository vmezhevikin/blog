package com.vmezhevikin.blog.repository.storage;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.vmezhevikin.blog.entity.Article;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {
	List<Article> findAll();
	
	int countByCategoryName(String name);
}