package edu.pnu;

import java.util.Date;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInit implements ApplicationRunner  {
	private final BoardRepository boardRepo;
	
	// 초기 데이터 넣기
	@Override
	public void run(ApplicationArguments args) throws Exception{
		
		for(int i = 1; i <= 10; i++) {
			Board board = new Board();
			board.setSeq((long)i);
			board.setTitle("계시판 프로그램 ");
			board.setWriter("도우너" + i);
			board.setContent("게시판 프로그램 테스트입니다...");
			board.setCreateDate(new Date());
			board.setCnt(0L);
			boardRepo.save(board);			
		}
		
	}
}
