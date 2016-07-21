package com.vmezhevikin.blog.repository.storage;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.vmezhevikin.blog.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Short> {
	List<Category> findAll(Sort sort);
}