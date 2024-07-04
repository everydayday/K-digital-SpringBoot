package edu.pnu.controller;

import java.util.Date;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;

@RestController
public class MemberController {
	private HashMap<String,MemberVO> map = new HashMap<>();
	
	public MemberController() {
		for (int i = 1 ; i <= 10 ; i++ ) {
			map.put(String.valueOf(i), MemberVO.builder()
			.id(i).name("name" + i)
			.pass("pass" + i)
			.regidate(new Date()).build());
			}
	}
	
	@GetMapping("/members")
	public ResponseEntity<?> getAllMember(){
		return ResponseEntity.badRequest().body(map);
	}
	
	
	// 검색(Read - select)
	@GetMapping("/member")
	public MemberVO getMemberById(Integer id) {
		
			if (map.containsKey(id))
				return map.get(id);
		
		return null;
	}
	// 입력(Create - insert)
	@PostMapping("/member")
	public MemberVO addMember(MemberVO memberVO) {
		if (getMemberById(memberVO.getId()) != null)
			return null;
	memberVO.setRegidate(new Date());
	map.put(String.valueOf(memberVO.getId()),memberVO);
	return memberVO;
	}

	// 수정(Update - update)
	@PutMapping("/member")
	public int updateMembers(MemberVO memberVO) {
		MemberVO m = getMemberById(memberVO.getId());
		if (m == null)
			return 0;
		m.setName(memberVO.getName());
		m.setPass(memberVO.getPass());
		return 1;
	}
	
	// 삭제(Delete - delete)
	@DeleteMapping("/member")
	public int removeMember(Integer id) {
		try {
			map.remove(String.valueOf(id));
		} catch(Exception e) {
			return 0;
		}
			return 1;
		}

	
	@PostMapping("/memberJSON")
	public MemberVO addMemberJSON(@RequestBody MemberVO memberVO) {
		return addMember(memberVO);
	}
	
	
}
