package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;
import lombok.RequiredArgsConstructor;

@RestController
public class MemberController {	
	
	 //1.
	@Autowired
	private MemberService memberService;
	
//	// 2.
//	private MemberService memberService;
//	
//	@Autowired
//	public MemberController(MemberService memberService) {
//	}
//		
//	
//	// 3.
//	private MemberService memberService;
//	
//	@Autowired
//	public void setMemberService(MemberService memberService) {
//		memberService = new MemberService();
//	}
//	
//	// 4.
//	@RequiredArgsConstructor
//	private final MemberService memberService;
//	
	
	
	@GetMapping("/members")
	public List<MemberVO> getAllMember(){
		return memberService.getAllMember();
	}
	
//	@GetMapping("/member/{id}")
//	public ResponseEntity<?> getMember(@PathVariable Integer id){
//		return ResponseEntity.ok(memberService.getAllMember());
//	}
	
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
