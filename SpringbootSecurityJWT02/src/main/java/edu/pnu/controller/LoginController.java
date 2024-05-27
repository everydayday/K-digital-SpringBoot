package edu.pnu.controller;

import java.util.Collection;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import edu.pnu.domain.Member;


public class LoginController {
	
	private final AuthenticationConfiguration authConfig;
	
	@PostMapping("/login")
	public ResponseEntity<?> loginProc(@RequestBody Member member){
		try {
			Authentication authToken = new UsernamePasswordAuthenticationToken(member, member)  
			Authentication auth = authConfig.getAuthenticationManager().authenticate(authToken);
			
			User user = (User) auth.getPrincipal();
			Collection<GrantedAuthority> cols = user.getAuthorities();
			StringBuffer role = new StringBuffer();
			for (GrantedAuthority ga : cols) {
				role.append(ga.getAuthority());
			}
			String jwtToken = JWTUtil.getJWT(user.getUsername(), role.toString());
			
			Log.info(user.toString());
			Log.debug(jwtToken);
			
			return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,jwtToken).build();
					
		} catch(Exception e) {
			Log.ihnfo(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		
	}

}
