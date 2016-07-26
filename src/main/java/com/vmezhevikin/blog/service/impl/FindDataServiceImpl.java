package com.vmezhevikin.blog.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vmezhevikin.blog.entity.Article;
import com.vmezhevikin.blog.entity.Author;
import com.vmezhevikin.blog.entity.Category;
import com.vmezhevikin.blog.entity.Comment;
import com.vmezhevikin.blog.model.CurrentUser;
import com.vmezhevikin.blog.repository.storage.ArticleRepository;
import com.vmezhevikin.blog.repository.storage.AuthorRepository;
import com.vmezhevikin.blog.repository.storage.CategoryRepository;
import com.vmezhevikin.blog.repository.storage.CommentRepository;
import com.vmezhevikin.blog.service.FindDataService;

@Service
public class FindDataServiceImpl implements FindDataService, UserDetailsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FindDataServiceImpl.class);
	
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
	public Author findAuthorById(Long id) {
		return authorRepository.findOne(id);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Author author = authorRepository.findByName(username);
		if (author != null) {
			return new CurrentUser(author);
		}
		else {
			LOGGER.error("Author not found by " + username);
			throw new UsernameNotFoundException("Profile not found by " + username);
		}
	}

	@Override
	public Page<Article> findAllArticles(Pageable pageable) {
		return articleRepository.findAll(pageable);
	}

	@Override
	public int countArticlesById(Long id) {
		return articleRepository.countById(id);
	}

	@Override
	public Article findArticleById(Long id) {
		return articleRepository.findOne(id);
	}

	@Override
	public List<Article> findAllArticlesForCategory(Short idCategory) {
		return articleRepository.findByCategoryId(idCategory);
	}

	@Override
	public Page<Article> findArticlesForCategory(Short idCategory, Pageable pageable) {
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
	public int countAllCommentsForArticle(Long idArticle) {
		return commentRepository.countAllByArticleId(idArticle);
	}

	@Override
	public List<Comment> findAllCommentsForArticle(Long idArticle) {
		return commentRepository.findAllByArticleId(idArticle);
	}

	@Override
	public List<Comment> findFirstNCommentsForArticle(Long idArticle, int number) {
		return commentRepository.findFirstNByArticleId(idArticle, number);
	}
}