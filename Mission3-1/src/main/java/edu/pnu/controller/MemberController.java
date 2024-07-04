package edu.pnu.controller;

import java.sql.SQLException;
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

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;
import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {
	

	private final MemberService memberService;	
	
	@GetMapping("/members")
	public List<MemberVO> getAllMember(){
		return memberService.getAllMember();
	}
	
	@GetMapping("/member/{id}")
	public ResponseEntity<?> getMember(@PathVariable Integer id){
//		if(id == null)
//			ResponseEntity.ok(memberService.getAllMember());
		return ResponseEntity.ok(memberService.getAllMember());
//		RETURN RESPONSEENTITY<T>
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
