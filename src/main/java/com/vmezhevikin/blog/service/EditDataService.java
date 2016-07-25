package com.vmezhevikin.blog.service;

import com.vmezhevikin.blog.entity.Author;
import com.vmezhevikin.blog.form.SignUpForm;

public interface EditDataService {
	
	void incrementViewsForArticle(Long idArticle);
	
	Author createNewAuthor(SignUpForm form);
}