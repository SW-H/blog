package com.seungwon.blog.application.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.seungwon.blog.application.dto.PostDTO;
import com.seungwon.blog.application.dto.PostRequestDTO;
import com.seungwon.blog.application.exception.EmptyDataException;
import com.seungwon.blog.application.exception.InvalidRequestException;
import com.seungwon.blog.domain.entity.Post;
import com.seungwon.blog.infra.PostRepository;

@Service
public class PostService {
	PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public long save(PostRequestDTO postRequestDTO) {
		Post post = Post.builder()
				.title(postRequestDTO.title())
				.content(postRequestDTO.content())
				.memberId(postRequestDTO.memberId())
				.build();

		postRepository.save(post);
		return post.getId();
	}

	public List<PostDTO> findAll() {
		List<Post> posts = postRepository.findAll();
		return posts.stream()
				.map(PostDTO::new)
				.toList();
	}

	public List<PostDTO> findByTitle(String title) {
		List<Post> posts = postRepository.findByTitle(title);

		if (posts.isEmpty()) {
			throw new EmptyDataException("해당 제목으로 검색된 결과가 없습니다.");
		}

		return posts.stream()
				.map(PostDTO::new)
				.toList();
	}

	public List<PostDTO> findByContent(String content) {
		List<Post> posts = postRepository.findByContent(content);

		if (posts.isEmpty()) {
			throw new EmptyDataException("해당 내용을 포함하는 검색 결과가 없습니다.");
		}

		return posts.stream()
				.map(PostDTO::new)
				.toList();
	}

	public List<PostDTO> findByMember(long memberId) {
		List<Post> posts = postRepository.findByMember(memberId);

		if (posts.isEmpty()) {
			throw new EmptyDataException("해당 작성자가 작성한 글이 없습니다.");
		}

		return posts.stream()
				.map(PostDTO::new)
				.toList();
	}

	public PostDTO findById(long id) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new EmptyDataException("해당하는 글이 존재하지 않습니다"));

		return new PostDTO(post);
	}

	public long update(long id, PostRequestDTO postRequestDTO) {
		PostDTO original = findById(id);
		long originalWriter = original.memberId();
		long writerToValidate = postRequestDTO.memberId();

		if (originalWriter != writerToValidate) {
			throw new InvalidRequestException("잘못된 요청입니다");
		}

		Post post = Post.builder()
				.id(id)
				.title(postRequestDTO.title())
				.content(postRequestDTO.content())
				.memberId(originalWriter)
				.updatedAt(LocalDateTime.now())
				.build();

		postRepository.update(post);

		return id;
	}

	public void deleteById(long id) {
		findById(id);
		postRepository.deleteById(id);
	}
}
