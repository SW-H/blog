package com.seungwon.blog.infra;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.seungwon.blog.domain.entity.Post;

@Mapper
public interface PostRepository {
	List<Post> findAll();

	Optional<Post> findByTitle(String title);

	Optional<Post> findByContent(String content);

	Optional<Post> findByWriter(long writerId);
}
