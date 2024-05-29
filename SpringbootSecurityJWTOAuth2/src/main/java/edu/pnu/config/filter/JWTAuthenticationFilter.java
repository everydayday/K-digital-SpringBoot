package edu.pnu.config.filter;

import java.io.IOException;
import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.pnu.domain.Member;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authenticationManager;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
										HttpServletResponse response) throws AuthenticationException{
		ObjectMapper mapper = new ObjectMapper();
		Member member = null;
		try {
			// null 값인지 판단하는 듯
			member = mapper.readValue(request.getInputStream(), Member.class);
		}catch(Exception e) {
			e.printStackTrace();
		}
		Authentication authToken =new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword());
		
		Authentication auth = authenticationManager.authenticate(authToken);
		System.out.println("auth:" + auth);
		
		return auth;
		
		
//		return super.attemptAuthentication(request, response);
	}
	
	// 응답 성공 시, 실행되는 듯
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse
			response, FilterChain chain, Authentication authResult) throws IOException, ServletException{
		
		User user = (User)authResult.getPrincipal();
		String token = JWT.create()
						.withExpiresAt(new Date(System.currentTimeMillis()+1000*60*10))
						.withClaim("username", user.getUsername())
						.sign(Algorithm.HMAC256("edu.pnu.jwt"));
		response.addHeader("Authorization", "Bearer " + token);
		
//		super.successfulAuthentication(request, response, chain, authResult);
	}
	
	
	
}
