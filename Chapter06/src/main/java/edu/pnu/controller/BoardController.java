package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.pnu.domain.Board;
import edu.pnu.service.BoardService;

@Controller
public class BoardController {
//	@GetMapping("/getBoardList")
//	public String getBoardList(Model model) {	// view 넘겨줄 데이터 저장용
//		List<Board> boardList = new ArrayList<Board>();
//		for(int i = 1; i <= 10; i++) {
//			Board board = new Board();
//			board.setSeq((long)i);
//			board.setTitle("계시판 프로그램 ");
//			board.setWriter("도우너");
//			board.setContent("게시판 프로그램 테스트입니다...");
//			board.setCreateDate(new Date());
//			board.setCnt(0L);
//			boardList.add(board);			
//		}
//		model.addAttribute("boardList",boardList);
//		return "getBoardList";
//	}
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/getBoardList")
	public String getBoardList(Model model, Board board) {
		List<Board> boardList = boardService.getBoardList(board);
		
		model.addAttribute("boardList", boardList);
		return "getBoardList";
	}
	
	@GetMapping("/insertBoard")
	public String insertBoardView() {
		return "insertBoard";
	}
	
	@PostMapping("/insertBoard")
	public String insertBoard(Board board) {
		boardService.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoard(Model model, Board board) {		
		model.addAttribute("board", boardService.getBoard(board));
		return "getBoard";
	} 
	
	// 이런 방법도 있다
	@GetMapping("/getBoard1")
	public ModelAndView getBoard1(Long seq) {
		Board b = boardService.getBoardBySeq(seq);
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("board", b);
		mv.setViewName("getBoard");
		
		return mv;
	}
	
	
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		boardService.updateBoard(board);
		return "forward:getBoardList";
	}	// forward는 같은 메서드 형식에게만 전송된다(post,get)
		// redirect는 페이지 새롭게 업데이트 해서 전송
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {		
		boardService.deleteBoard(board);
		return "forward:getBoardList";
	}

}
