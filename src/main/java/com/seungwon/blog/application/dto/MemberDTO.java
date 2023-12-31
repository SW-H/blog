package com.seungwon.blog.application.dto;

import java.time.LocalDateTime;

import com.seungwon.blog.domain.entity.Member;

public record MemberDTO(

		String nickName,

		String email,

		String password,

		LocalDateTime updatedAt
) {
	public MemberDTO(Member member) {
		this(
				member.getNickName(),
				member.getEmail(),
				member.getPassword(),
				member.getUpdatedAt()
		);
	}
}
