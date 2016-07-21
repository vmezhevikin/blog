package com.vmezhevikin.blog.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.joda.time.DateTime;

@Entity
@Table(name = "article")
public class Article implements Serializable {

	private static final long serialVersionUID = -3003691908445864682L;

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_category", nullable = false)
	private Category category;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_author", nullable = false)
	private Author author;
	
	@OneToMany(targetEntity = Comment.class, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, mappedBy = "article", orphanRemoval = true, fetch = FetchType.LAZY)
	@OrderBy("date DESC")
	private List<Comment> comments;
	
	@Column(name = "img", nullable = false)
	private String image;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private String text;
	
	@Column(nullable = false)
	private Timestamp date;
	
	@Column(nullable = false)
	private Integer views;

	public Article() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	@Transient
	public String getDateAsFormattedString() {
		return new DateTime(date).toString("EEEE, MMMM dd, yyyy hh:mm aa");
	}

	// TODO hash equals
}