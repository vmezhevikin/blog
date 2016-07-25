package com.vmezhevikin.blog.repository.storage;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.vmezhevikin.blog.entity.Author;

public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {
	List<Author> findAll();
	
	Author findByName(String name);
}