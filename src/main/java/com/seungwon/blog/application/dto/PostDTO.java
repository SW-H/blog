package com.seungwon.blog.application.dto;

import java.time.LocalDateTime;

import com.seungwon.blog.domain.entity.Post;

import lombok.Builder;

@Builder
public record PostDTO(
		long id,
		String title,

		String content,

		// long categoryId,

		long memberId,

		int hits,

		LocalDateTime updatedAt

) {
	public PostDTO(Post post) {
		this(
				post.getId(),
				post.getTitle(),
				post.getContent(),
				post.getMemberId(),
				post.getHits(),
				post.getUpdatedAt()
		);
	}
}
