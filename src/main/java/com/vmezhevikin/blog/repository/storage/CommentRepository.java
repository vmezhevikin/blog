package com.vmezhevikin.blog.repository.storage;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.vmezhevikin.blog.entity.Comment;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {
	
}