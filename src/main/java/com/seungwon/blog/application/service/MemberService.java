package com.seungwon.blog.application.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.seungwon.blog.application.dto.MemberDTO;
import com.seungwon.blog.application.dto.MemberRequestDTO;
import com.seungwon.blog.application.exception.EmptyDataException;
import com.seungwon.blog.application.exception.InvalidRequestException;
import com.seungwon.blog.domain.entity.Member;
import com.seungwon.blog.infra.MemberRepository;

@Service
public class MemberService {
	MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public long save(MemberRequestDTO memberRequestDTO) {
		Member member = Member.builder()
				.email(memberRequestDTO.email())
				.password(memberRequestDTO.password())
				.nickName(memberRequestDTO.nickName())
				.build();

		memberRepository.save(member);
		return member.getId();
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

	public MemberDTO findById(long id) {
		Member member = memberRepository.findById(id)
				.orElseThrow(() -> new EmptyDataException("존재하지 않는 사용자입니다"));

		return new MemberDTO(member);
	}

	public long update(long id, MemberDTO memberDTO) {
		MemberDTO original = findById(id);
		String originalEmail = original.email();
		String emailToValidate = memberDTO.email();

		if (!originalEmail.equals(emailToValidate)) {
			throw new InvalidRequestException("잘못된 요청입니다");
		}

		Member member = Member.builder()
				.id(id)
				.nickName(memberDTO.nickName())
				.email(memberDTO.email())
				.password(memberDTO.password())
				.updatedAt(LocalDateTime.now())
				.build();

		memberRepository.update(member);

		return id;
	}

	public void deleteById(long id) {
		findById(id);
		memberRepository.deleteById(id);
	}
}
