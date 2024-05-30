package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;

@Controller
public class LoginController {
	@Autowired
	private MemberService memberService;
	
	
	
	@GetMapping("/oauth")
	public @ResponseBody String auth(@AuthenticationPrincipal OAuth2User user) {
		if (user == null) return "OAuth2:null";
		
		System.out.println("attributes:" + user.getAttributes());
		
		return "OAuth2:" + user;
	}
	
	@GetMapping("/login")
	public void login() {
		System.out.println("login 요청");
	}
	
	@GetMapping("/loginSuccess")
	public void loginSuccess() {
		System.out.println("loginSuccess 요청");
	}
	
	@GetMapping("/accessDenied")
	public void accessDenied() {
		System.out.println("accessDenied");
	}
	
	@GetMapping("/auth")
	public @ResponseBody String auth(@AuthenticationPrincipal User user) {
		
		if(user == null)		
		System.out.println("auth");
		
		return user.toString();
	}
	
	@GetMapping("/join")
	public void join() {}
	
	@PostMapping("/join")
	public String joinProc(Member member) {
		memberService.save(member);
		return "welcome";
	}
	
}
