package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {

	private MemberService memberService;
	
	public MemberController() {
		memberService = new MemberService();
	}
	
	@GetMapping("/members")
	public List<MemberVO> getAllMember(){
		//return edu.pnu.service.MemberService.getAllMember(list);
		return memberService.getAllMember();
	}
	
	
	
	@GetMapping("/member")
	public MemberVO getMemberById(Integer id) {
		return memberService.getMemberById(id);
	} 	
	
	@PostMapping("/member")
	public void addMember(MemberVO memberVO) {
		memberService.addMember(memberVO);
		
	}
	
	@PutMapping("/member")
	public int updateMembers(MemberVO memberVO) {
		return memberService.updateMembers(memberVO);
	}
	
	@DeleteMapping("/member")
	public int removeMember(Integer id) {
		return memberService.removeMember(id);
	}
	
	@PostMapping("/memberJSON")
	public void addMemberJSON(@RequestBody MemberVO memberVO) {
		memberService.addMemberJSON(memberVO);
	}
	
	
	
}
