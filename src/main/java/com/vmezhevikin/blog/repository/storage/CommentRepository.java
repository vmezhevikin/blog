package com.vmezhevikin.blog.repository.storage;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.vmezhevikin.blog.entity.Comment;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {
	
	@Query(value = "select count(id) from blog.comment where id_article = ?1", nativeQuery = true)
	int countAllByArticleId(Long idArticle);
	
	@Query(value = "select * from blog.comment where (id_article = ?1) order by date desc", nativeQuery = true)
	List<Comment> findAllByArticleId(Long idArticle);
	
	@Query(value = "select * from blog.comment where (id_article = ?1) order by date desc limit ?2", nativeQuery = true)
	List<Comment> findFirstNByArticleId(Long idArticle, int number);
}