package com.seungwon.blog.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.seungwon.blog.application.exception.InvalidDataException;
import com.seungwon.blog.domain.entity.Member;

class MemberTest {
	static Member member;

	@BeforeAll
	static void init() {
		member = Member.builder()
				.nickName("master")
				.email("tester@naver.com")
				.password("Tester1234")
				.build();
	}

	@Nested
	@DisplayName("이메일 형식이")
	class Email {
		@ParameterizedTest
		@ValueSource(strings = {
				"",
				"email@",
				"naver.com"
		})
		@DisplayName("유효하지 않은 경우 InvalidDataException 을 던진다")
		void is_invalid(String email) {
			//given

			//when,then
			assertThrows(InvalidDataException.class, () -> member.changeEmail(email));
		}
	}

	/*
	 * [닉네임]
	 * 10자 이내
	 * */
	@Nested
	@DisplayName("닉네임이")
	class NickName {
		@ParameterizedTest
		@ValueSource(strings = {
				"",
				"NickNameWithTwentyOne"
		})
		@DisplayName("유효하지 않은 경우 InvalidDataException 을 던진다 : 빈문자열/20자 넘는 경우")
		void is_invalid(String nickname) {
			//given

			//when,then
			assertThrows(InvalidDataException.class, () -> member.changeNickName(nickname));
		}
	}

	/*
	 * [비밀번호]
	 * 최소 길이 : 8글자
	 * 최대 길이 : 30자
	 * 영어 대문자, 소문자, 숫자 각 1개 이상씩 포함
	 * 특수문자 가능
	 * */
	@Nested
	@DisplayName("비밀번호가")
	class Password {
		@ParameterizedTest
		@ValueSource(strings = {
				"Short1",
				"without_upper_case_1234",
				"WITHOUT_LOWER_CASE_1234",
				"Everything_ok_but_too_long_1234"
		})
		@DisplayName("유효하지 않은 경우 InvalidDataException 을 던진다")
		void is_invalid(String password) {
			//given

			//when,then
			assertThrows(InvalidDataException.class, () -> member.changePassword(password));
		}
	}

}
