package com.seungwon.blog.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.seungwon.blog.application.dto.PostDTO;
import com.seungwon.blog.domain.entity.Post;
import com.seungwon.blog.infra.PostRepository;

@Service
public class PostService {
	PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public List<PostDTO> findAll() {
		List<Post> posts = postRepository.findAll();
		return posts.stream()
				.map(PostDTO::new)
				.toList();
	}
}
