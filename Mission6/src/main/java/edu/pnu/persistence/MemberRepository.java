package edu.pnu.persistence;

import org.springframework.data.repository.CrudRepository;

import edu.pnu.domain.Member;

public interface MemberRepository extends CrudRepository<Member, String> {
	//Member findById(String id); 	// List로 설정 시 오류
	
}
