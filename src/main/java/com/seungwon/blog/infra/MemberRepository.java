package com.seungwon.blog.infra;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.seungwon.blog.domain.entity.Member;

@Mapper
public interface MemberRepository {
	List<Member> findAll();

	Optional<Member> findByEmail(String email);

	List<Member> findByNickName(String nickName);

}
