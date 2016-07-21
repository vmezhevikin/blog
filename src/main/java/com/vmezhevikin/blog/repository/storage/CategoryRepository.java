package com.vmezhevikin.blog.repository.storage;

import org.springframework.data.repository.CrudRepository;

import com.vmezhevikin.blog.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Short> {
	
}