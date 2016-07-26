package com.vmezhevikin.blog.form;

public class CommentForm {

	private Long authorId;
	private Long articleId;
	private String text;

	public CommentForm() {
		super();
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}