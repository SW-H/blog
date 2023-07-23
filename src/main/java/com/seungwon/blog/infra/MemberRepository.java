package com.seungwon.blog.infra;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import com.seungwon.blog.domain.entity.Member;

@Mapper
@Transactional
public interface MemberRepository {
	long save(Member member);

	List<Member> findAll();

	Optional<Member> findByEmail(String email);

	List<Member> findByNickName(String nickName);

	Optional<Member> findById(long id);

	long update(Member member);

	void deleteById(long id);

}
