package com.seungwon.blog.application.dto;

import lombok.Builder;

@Builder
public record MemberRequestDTO(
		String nickName,
		String email,
		String password
) {
	// public MemberRequestDTO(String nickName, String email, String password) {
	// 	this.nickName = nickName;
	// 	this.email = email;
	// 	this.password = password;
	// }
}
