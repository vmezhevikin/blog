package com.vmezhevikin.blog.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vmezhevikin.blog.entity.Author;
import com.vmezhevikin.blog.form.SignUpForm;
import com.vmezhevikin.blog.repository.storage.ArticleRepository;
import com.vmezhevikin.blog.repository.storage.AuthorRepository;
/*import com.vmezhevikin.blog.repository.storage.CategoryRepository;
import com.vmezhevikin.blog.repository.storage.CommentRepository;*/
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
	
	/*@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CommentRepository commentRepository;*/

	@Override
	@Transactional
	public void incrementViewsForArticle(Long idArticle) {
		articleRepository.incrementViewsForArticle(idArticle);
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
}