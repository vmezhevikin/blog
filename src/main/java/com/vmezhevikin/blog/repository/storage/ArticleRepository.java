package com.vmezhevikin.blog.repository.storage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.vmezhevikin.blog.entity.Article;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {

	Page<Article> findAll(Pageable pageable);

	int countByCategoryName(String name);

	Page<Article> findByCategoryId(Short idCategory, Pageable pageable);
}