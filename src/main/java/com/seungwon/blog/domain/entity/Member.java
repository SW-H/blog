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
	private Long id;

	@NonNull
	private String nickName;

	@NonNull
	private String email;

	@NonNull
	private String password;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	private MemberStatus status;

	private final Pattern EMAIL_PATTERN = Pattern.compile("^([\\w\\.\\-_]+)?\\w+@[\\w-_]+(\\.\\w+)+$");
	private final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,30}$");

	@Builder
	public Member(Long id, @NonNull String nickName, @NonNull String email, @NonNull String password,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.nickName = nickName;
		this.email = email;
		this.password = password;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		status = MemberStatus.NORMAL;
	}

	@Builder//
	public Member(@NonNull String nickName, @NonNull String email, @NonNull String password) {
		validateData(nickName, email, password);
		this.nickName = nickName;
		this.email = email;
		this.password = password;
		createdAt = LocalDateTime.now();
		updatedAt = createdAt;
		status = MemberStatus.NORMAL;
	}

	@Builder
	public Member(long id, @NonNull String nickName, @NonNull String email, @NonNull String password) {
		validateData(nickName, email, password);
		this.id = id;
		this.nickName = nickName;
		this.email = email;
		this.password = password;
		status = MemberStatus.NORMAL;
	}

	@Builder
	public Member(@NonNull String email, @NonNull String password) {
		validateEmail(email);
		validatePassWord(password);
		this.email = email;
		this.password = password;
		nickName = email.split("@")[0];
		createdAt = LocalDateTime.now();
		updatedAt = createdAt;
		status = MemberStatus.NORMAL;
	}

	private void validateData(@NonNull String nickName, @NonNull String email, @NonNull String password) {
		validateNickName(nickName);
		validateEmail(email);
		validatePassWord(password);
	}

	private void validateEmail(String email) {
		if (!EMAIL_PATTERN.matcher(email).matches()) {
			throw new InvalidDataException(email);
		}
	}

	private void validatePassWord(String password) {
		if (!PASSWORD_PATTERN.matcher(password).matches()) {
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
