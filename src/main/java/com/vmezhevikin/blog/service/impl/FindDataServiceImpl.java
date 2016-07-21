package com.vmezhevikin.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vmezhevikin.blog.entity.Article;
import com.vmezhevikin.blog.entity.Author;
import com.vmezhevikin.blog.entity.Category;
import com.vmezhevikin.blog.entity.Comment;
import com.vmezhevikin.blog.repository.storage.ArticleRepository;
import com.vmezhevikin.blog.repository.storage.AuthorRepository;
import com.vmezhevikin.blog.repository.storage.CategoryRepository;
import com.vmezhevikin.blog.repository.storage.CommentRepository;
import com.vmezhevikin.blog.service.FindDataService;

@Service
public class FindDataServiceImpl implements FindDataService {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CommentRepository commentRepository;

	@Override
	public List<Author> findAllAuthors() {
		return authorRepository.findAll();
	}

	@Override
	public Page<Article> findAllArticles(Pageable pageable) {
		return articleRepository.findAll(pageable);
	}

	@Override
	public Article findArticleById(Long id) {
		return articleRepository.findOne(id);
	}

	@Override
	public Page<Article> findAllArticlesByCategoryId(Short idCategory, Pageable pageable) {
		return articleRepository.findByCategoryId(idCategory, pageable);
	}

	@Override
	public List<Category> findAllCategoriesWithStatistic() {
		List<Category> categories = categoryRepository.findAll(new Sort("id"));
		for (Category category : categories) {
			int count = articleRepository.countByCategoryName(category.getName());
			category.setCount(count);
		}
		return categories;
	}

	@Override
	public Category findCategoryById(Short id) {
		return categoryRepository.findOne(id);
	}

	@Override
	public List<Comment> findAllCommentsByArticleId(Long idArticle) {
		return commentRepository.findByArtcileId(idArticle);
	}
}