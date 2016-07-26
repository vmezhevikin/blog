package com.vmezhevikin.blog.service;

import com.vmezhevikin.blog.entity.Author;
import com.vmezhevikin.blog.entity.Comment;
import com.vmezhevikin.blog.form.CommentForm;
import com.vmezhevikin.blog.form.SignUpForm;

public interface EditDataService {
	
	void incrementViewsForArticle(Long articleId);
	
	Author createNewAuthor(SignUpForm form);
	
	Comment createNewComment(CommentForm form);
	
	void removeComment(Long commentId);
}