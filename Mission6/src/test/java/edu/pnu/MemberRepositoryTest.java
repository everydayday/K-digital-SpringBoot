package edu.pnu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@SpringBootTest
public class MemberRepositoryTest {
	@Autowired private MemberRepository memberRepo;
	
	//@Test
	public void testInsertMember() {
		Member member = Member.builder()
						.id("id")
						.password("password")
						.name("name")
						.role("role")
						.build();
		memberRepo.save(member);
	}
	
	@Test
	public void testGetMember() {
		Member member = memberRepo.findById("id").get();
		System.out.println(member);
	}
	
	@Test
	public void testUpdateMember() {
		Member member = memberRepo.findById("id").get();
		memberRepo.save(member);		
	}
	
	@Test
	public void testDeleteMember() {
		memberRepo.deleteById("id");		
	}
	
	
	
}
