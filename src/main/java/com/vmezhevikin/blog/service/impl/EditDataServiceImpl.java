package com.vmezhevikin.blog.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vmezhevikin.blog.entity.Author;
import com.vmezhevikin.blog.entity.Comment;
import com.vmezhevikin.blog.form.CommentForm;
import com.vmezhevikin.blog.form.SignUpForm;
import com.vmezhevikin.blog.repository.storage.ArticleRepository;
import com.vmezhevikin.blog.repository.storage.AuthorRepository;
import com.vmezhevikin.blog.repository.storage.CommentRepository;
import com.vmezhevikin.blog.service.EditDataService;

@Service
public class EditDataServiceImpl implements EditDataService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EditDataServiceImpl.class);
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CommentRepository commentRepository;

	@Override
	@Transactional
	public void incrementViewsForArticle(Long articleId) {
		articleRepository.incrementViewsForArticle(articleId);
	}

	@Override
	@Transactional
	public Author createNewAuthor(SignUpForm form) {
		LOGGER.debug("Creating new author {}", form.getName());
		Author author = new Author();
		author.setName(form.getName());
		author.setEmail(form.getEmail());
		author.setPassword(passwordEncoder.encode(form.getPassword()));
		authorRepository.save(author);
		LOGGER.info("New author created {}", form.getName());
		return author;
	}

	@Override
	@Transactional
	public Comment createNewComment(CommentForm form) {
		LOGGER.debug("Creating new comment articleId={}, authorId={}", form.getArticleId(), form.getAuthorId());
		Comment comment = new Comment();
		comment.setArticle(articleRepository.findOne(form.getArticleId()));
		comment.setAuthor(authorRepository.findOne(form.getAuthorId()));
		comment.setText(form.getText());
		comment.setDate(new Timestamp(new Date().getTime()));
		commentRepository.save(comment);
		LOGGER.info("New comment articleId={}, authorId={} created", form.getArticleId(), form.getAuthorId());
		return comment;
	}

	@Override
	@Transactional
	public void removeComment(Long commentId) {
		LOGGER.debug("Removing comment id={}", commentId);
		commentRepository.delete(commentId);
		LOGGER.info("Comment id={} removed", commentId);
	}
}