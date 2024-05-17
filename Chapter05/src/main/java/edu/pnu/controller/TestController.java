package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@RestController
public class TestController {
	
	@Autowired
	private BoardRepository boardRepo;
	
	@GetMapping("/board")
	public Board getBoard(Long seq) {
		return boardRepo.findById(seq).get();	
	}
	
	@GetMapping("/boards")
	public List<Board> getBoards(){
		return boardRepo.findAll();
	}
	
	@PostMapping("/board")
	public Board addBoard(Board board) {
		return boardRepo.save(board); 	// 같은 id 있을시 duplicate 에러 발생
	}	
	
	// boardRepo 인터페이스지만 실행 시점에 코드 만들어서 올려줌
	@PutMapping("/board")
	public Board updateBoard(@RequestBody Board board) {
		Board b = boardRepo.findById(board.getSeql()).get();
		b.update(board);	// update 만들어서 하는 방법
		
		return boardRepo.save(b);
		
		// 내가 한 방법
//		b.setContent(board.getContent());
//		b.setTitle(board.getTitle());
//		b.setWriter(board.getWriter());
//		boardRepo.save(b);
//		return b;		
	}
	
	@DeleteMapping("/board/{seq}")
	public void removeBoard(@PathVariable Long seq) {
		
		boardRepo.deleteById(seq);
	}
	
}
