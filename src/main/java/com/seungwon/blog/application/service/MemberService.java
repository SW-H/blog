package com.seungwon.blog.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.seungwon.blog.application.dto.MemberDTO;
import com.seungwon.blog.application.exception.EmptyDataException;
import com.seungwon.blog.domain.entity.Member;
import com.seungwon.blog.infra.MemberRepository;

@Service
public class MemberService {
	MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public List<MemberDTO> findAll() {
		List<Member> members = memberRepository.findAll();

		return members.stream()
				.map(MemberDTO::new)
				.toList();

	}

	public MemberDTO findByEmail(String email) {
		Member member = memberRepository.findByEmail(email)
				.orElseThrow(() -> new EmptyDataException("존재하지 않는 이메일입니다"));

		return new MemberDTO(member);
	}

	public List<MemberDTO> findByNickName(String nickName) {
		List<Member> members = memberRepository.findByNickName(nickName);

		if (members.isEmpty()) {
			throw new EmptyDataException("존재하지 않는 닉네임입니다");
		}

		return members.stream()
				.map(MemberDTO::new)
				.toList();
	}
}
