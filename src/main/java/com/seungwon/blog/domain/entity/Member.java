package com.seungwon.blog.domain.entity;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

import com.seungwon.blog.application.exception.InvalidDataException;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@Data
@Getter
public class Member {
	private final LocalDateTime createdAt;

	private Long id;

	@NonNull
	private String nickName;

	@NonNull
	private String email;

	@NonNull
	private String password;

	private LocalDateTime updatedAt;

	@Builder
	public Member(@NonNull String nickName, @NonNull String email, @NonNull String password) {
		validateData(nickName, email, password);
		this.nickName = nickName;
		this.email = email;
		this.password = password;
		createdAt = LocalDateTime.now();
		updatedAt = createdAt;
	}

	private void validateData(@NonNull String nickName, @NonNull String email, @NonNull String password) {
		validateNickName(nickName);
		validateEmail(email);
		validatePassWord(password);
	}

	private void validateEmail(String email) {
		String emailFormat = "^([\\w\\.\\-_]+)?\\w+@[\\w-_]+(\\.\\w+)+$";
		Pattern pattern = Pattern.compile(emailFormat);
		if (!pattern.matcher(email).matches()) {
			throw new InvalidDataException(email);
		}
	}

	private void validatePassWord(String password) {
		Pattern passwordFormat = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,30}$");
		if (!passwordFormat.matcher(password).matches()) {
			throw new InvalidDataException(password);
		}
	}

	void validateNickName(String nickname) {
		if (nickname.isEmpty() || nickname.length() > 20) {
			throw new InvalidDataException(password);
		}
	}

	public void changeEmail(String email) {
		validateEmail(email);
		this.email = email;
		updatedAt = LocalDateTime.now();
	}

	public void changePassword(String password) {
		validatePassWord(password);
		this.password = password;
		updatedAt = LocalDateTime.now();
	}

	public void changeNickName(String nickName) {
		validateNickName(nickName);
		this.nickName = nickName;
		updatedAt = LocalDateTime.now();
	}
}
