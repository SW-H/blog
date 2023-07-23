package com.seungwon.blog.domain.entity;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@Data
@Getter
public class Post {
	private long id;

	@NonNull
	private String title;

	@NonNull
	private String content;

	private long memberId;

	private int hits;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	@Builder
	public Post(long id, @NonNull String title, @NonNull String content, long memberId, int hits,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.memberId = memberId;
		this.hits = hits;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	@Builder
	public Post(@NonNull String title, @NonNull String content, long memberId) {
		this.title = title;
		this.content = content;
		this.memberId = memberId;
		createdAt = LocalDateTime.now();
		updatedAt = createdAt;
		hits = 0;
	}

	public void updateHits() {
		hits++;
	}
}
