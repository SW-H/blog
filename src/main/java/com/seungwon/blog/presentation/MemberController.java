package com.seungwon.blog.presentation;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seungwon.blog.application.dto.MemberDTO;
import com.seungwon.blog.application.dto.MemberRequestDTO;
import com.seungwon.blog.application.service.MemberService;

@RestController
@RequestMapping("/members")
public class MemberController {
	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@PostMapping()
	long save(@RequestBody MemberRequestDTO memberRequestDTO) {
		return memberService.save(memberRequestDTO);
	}

	@GetMapping()
	public List<MemberDTO> findAll() {
		return memberService.findAll();
	}

	@GetMapping(params = "nickName")
	public List<MemberDTO> findByNickName(@RequestParam String nickName) {
		return memberService.findByNickName(nickName);
	}

	@GetMapping(params = "email")
	public MemberDTO findByEmail(@RequestParam String email) {
		return memberService.findByEmail(email);
	}

	@GetMapping(params = "id")
	public MemberDTO findById(@RequestParam long id) {
		return memberService.findById(id);
	}

	@PatchMapping("/{id}")
	long update(@PathVariable long id, @RequestBody MemberDTO memberDTO) {
		return memberService.update(id, memberDTO);
	}

	@DeleteMapping("/{id}")
	void deleteById(@PathVariable long id) {
		memberService.deleteById(id);
	}
}
