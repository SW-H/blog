package com.seungwon.blog.application.dto;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record PostRequestDTO(
		String title,
		String content,
		long memberId,
		LocalDateTime updatedAt
) {
	public PostRequestDTO(String title, String content, long memberId) {
		this(
				title,
				content,
				memberId,
				LocalDateTime.now()
		);
	}
}
