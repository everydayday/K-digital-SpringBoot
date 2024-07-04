package edu.pnu.persistence;

import org.springframework.data.repository.CrudRepository;

import edu.pnu.domain.Member;

// 인터페이스에 어노테이션 없는 이유 : 하이버네이트가 인터페이스 코드 만들면서 
// 자동으로 위에 컴퍼넌트 만들어서 올려줌
public interface MemberRepository extends CrudRepository<Member,String> {
	
	
}
