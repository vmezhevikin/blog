package com.vmezhevikin.blog.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmezhevikin.blog.entity.Category;
import com.vmezhevikin.blog.repository.storage.CategoryRepository;
import com.vmezhevikin.blog.service.StaticDataService;

@Service
public class StaticDataServiceImpl implements StaticDataService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	private List<String> categoryNames;
	
	@PostConstruct
	private void init() {	
		categoryNames = new ArrayList<>();
		Iterable<Category> categories = categoryRepository.findAll();
		for (Category category : categories) {
			categoryNames.add(category.getName());
		}
	}
	
	@Override
	public List<String> findCategoryNames() {
		return categoryNames;
	}
}