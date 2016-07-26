package com.vmezhevikin.blog.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vmezhevikin.blog.entity.Article;
import com.vmezhevikin.blog.entity.Author;
import com.vmezhevikin.blog.entity.Category;
import com.vmezhevikin.blog.entity.Comment;

public interface FindDataService {
	
	List<Author> findAllAuthors();
	
	Author findAuthorById(Long id);

	Page<Article> findAllArticles(Pageable pageable);
	
	int countArticlesById(Long id);
	
	Article findArticleById(Long id);
	
	List<Article> findAllArticlesForCategory(Short idCategory);
	
	Page<Article> findArticlesForCategory(Short idCategory, Pageable pageable);

	List<Category> findAllCategoriesWithStatistic();
	
	Category findCategoryById(Short id);
	
	int countAllCommentsForArticle(Long idArticle);
	
	List<Comment> findAllCommentsForArticle(Long idArticle);
	
	List<Comment> findFirstNCommentsForArticle(Long idArticle, int number);
}