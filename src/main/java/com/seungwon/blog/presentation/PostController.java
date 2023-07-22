package com.seungwon.blog.presentation;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seungwon.blog.application.dto.PostDTO;
import com.seungwon.blog.application.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {
	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping()
	List<PostDTO> findAll() {
		return postService.findAll();
	}

}
