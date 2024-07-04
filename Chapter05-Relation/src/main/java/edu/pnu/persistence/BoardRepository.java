package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.pnu.domain.Board;
// 인터페이스에 어노테이션 없는 이유 : 하이버네이트가 인터페이스 코드 만들면서 
// 자동으로 위에 컴퍼넌트 만들어서 올려줌
public interface BoardRepository extends JpaRepository<Board,Long> {
	List<Board> findByTitle(String searchKeyword);
	List<Board> findByContentContaining(String searchKeyword);
	List<Board> findByTitleContainingOrContentContaining(String title, String content);
	List<Board> findByTitleContainingOrderBySeqDesc(String searchKeyword);
	List<Board> findByTitleContainingAndCntGreaterThan(String title, Long cnt);
	List<Board> findByCntBetweenOrderBySeq(Long cnt1, Long cnt2);
	List<Board> findByTitleContainingOrContentContainingOrderBySeqDesc(String title, String content);
	//List<Board> findByTitleContaining(String searchKeyword, Pageable paging);
	Page<Board> findByTitleContaining(String searchKeyword, Pageable paging);
	@Query("SELECT b FROM Board b WHERE b.title like %?1% ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest1(String searchKeyword);
	//@Query("SELECT b FROM Board b WHERE b.title like %searchKeyword% ORDER BY b.seq DESC")
	//<Board> queryAnnotationTest1(String searchKeyword);
	@Query("SELECT b.seq, b.title, b.writer, b.createDate "
			+ "FROM Board b "
			+ "WHERE b.title like %?1% "
			+ "ORDER BY b.seq DESC")
	List<Object[]> queryAnnotationTest2(@Param("searchKeyword") String searchKeyword);
	
	@Query(value="select seq, title, writer, createdate "
			+ "from board where title like '%'||?1||'%' "
			+ "order by seq desc", nativeQuery=true)
	List<Object[]> queryAnnotationTest3(String searchKeyword);
	
}
