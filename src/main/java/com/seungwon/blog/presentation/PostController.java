package com.seungwon.blog.presentation;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seungwon.blog.application.dto.PostDTO;
import com.seungwon.blog.application.dto.PostRequestDTO;
import com.seungwon.blog.application.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {
	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@PostMapping()
	public long save(@RequestBody PostRequestDTO postRequestDTO) {
		return postService.save(postRequestDTO);
	}

	@GetMapping()
	public List<PostDTO> findAll() {
		return postService.findAll();
	}

	@GetMapping(params = "title")
	public List<PostDTO> findByTitle(@RequestParam String title) {
		return postService.findByTitle(title);
	}

	@GetMapping(params = "content")
	public List<PostDTO> findByContent(@RequestParam String content) {
		return postService.findByContent(content);
	}

	@GetMapping(params = "memberId")
	public List<PostDTO> findByMember(@RequestParam long memberId) {
		return postService.findByMember(memberId);
	}

	@GetMapping(params = "id")
	public PostDTO findById(@RequestParam long id) {
		return postService.findById(id);
	}

	@PatchMapping({"/{id}"})
	public long update(@PathVariable long id, @RequestBody PostRequestDTO postRequestDTO) {
		return postService.update(id, postRequestDTO);
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable long id) {
		postService.deleteById(id);
	}

}
