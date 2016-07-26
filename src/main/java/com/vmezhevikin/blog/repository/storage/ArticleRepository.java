package com.vmezhevikin.blog.repository.storage;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.vmezhevikin.blog.entity.Article;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {

	Page<Article> findAll(Pageable pageable);

	int countByCategoryName(String name);
	
	int countById(Long id);
	
	List<Article> findByCategoryId(Short categoryId);

	Page<Article> findByCategoryId(Short categoryId, Pageable pageable);
	
	@Modifying
	@Query(value = "update blog.article set views = views + 1 where id = ?1", nativeQuery = true)
	void incrementViewsForArticle(Long articleId);
}