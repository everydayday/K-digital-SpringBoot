package edu.pnu;

import java.util.Date;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.persistence.BoardRepository;
import edu.pnu.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

@Component	// bean 따로 등록하지 않아도 bean 사용가능
@RequiredArgsConstructor	// private final bean 부분 생성자 자동 생성
public class DataInit implements ApplicationRunner  {
	private final BoardRepository boardRepo;
	private final MemberRepository memberRepo;
	// 초기 데이터 넣기
	@Override
	public void run(ApplicationArguments args) throws Exception{
		
		memberRepo.save(Member.builder().id("member1").password("abcd").name("둘리")
										.role("ROLE_USER").build());
		memberRepo.save(Member.builder().id("member2").password("abcd").name("도우너")
				.role("ROLE_ADMIN").build());
		
		for(int i = 1; i <= 10; i++) {
			Board board = new Board();
			board.setSeq((long)i);
			board.setTitle("게시판 프로그램 ");
			board.setWriter("도우너" + i);
			board.setContent("게시판 프로그램 테스트입니다...");
			board.setCreateDate(new Date());
			board.setCnt(0L);
			boardRepo.save(board);			
		}
		
	}
}
