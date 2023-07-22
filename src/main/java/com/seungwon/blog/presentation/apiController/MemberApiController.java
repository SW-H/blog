package com.seungwon.blog.presentation.apiController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seungwon.blog.application.dto.MemberDTO;
import com.seungwon.blog.application.service.MemberService;

@RestController
@RequestMapping("/api/members")
public class MemberApiController {
	private final MemberService memberService;

	public MemberApiController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping()
	public ResponseEntity<List<MemberDTO>> findAll() {
		return ResponseEntity.ok(memberService.findAll());
	}

	@GetMapping(params = "nickName")
	public List<MemberDTO> findByNickName(@RequestParam String nickName) {
		return memberService.findByNickName(nickName);
	}

	@GetMapping(params = "email")
	public MemberDTO findByEmail(@RequestParam String email) {
		return memberService.findByEmail(email);
	}
}
