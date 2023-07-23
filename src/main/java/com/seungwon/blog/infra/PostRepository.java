package com.seungwon.blog.infra;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.seungwon.blog.domain.entity.Post;

@Mapper
public interface PostRepository {
	List<Post> findAll();

	List<Post> findByTitle(String title);

	List<Post> findByContent(String content);

	List<Post> findByMember(long writerId);

	Optional<Post> findById(long id);

	long save(Post post);

	long update(Post post);

	void deleteById(long id);
}
