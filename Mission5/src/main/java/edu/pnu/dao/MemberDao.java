package edu.pnu.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.MemberVO;

@Repository
public class MemberDao {
	
	@Autowired
	private DataSource dataSource;
//	private Connection con;
//	private PreparedStatement psmt;
//	private ResultSet rs;
//	
//	public MemberDao() {
//		
//		try {			
//			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/mission3","sa","abcd");
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public List<MemberVO> getAllMember() {
		List<MemberVO> list = new ArrayList<MemberVO>(); 
		Statement st = null;
		ResultSet rs = null;
		try {
			st = dataSource.getConnection().createStatement();

			rs = st.executeQuery("SELECT * FROM MEMBER");
			
			while(rs.next()) {
				MemberVO m = MemberVO.builder().id(rs.getInt("id"))
												.name(rs.getString("name"))
												.pass(rs.getString("pass"))
												.regidate(rs.getDate("regidate"))
												.build();
				list.add(m);

				
			}
		
		}catch(Exception e){
			e.printStackTrace();
		} finally {			

		}		
		
		return list;		
	}
	
	public MemberVO getMember(Integer id) {
		return null;
	}
	
	public MemberVO getMemberById(Integer id) {
//		System.out.println("id : " + id); // id : 456
		MemberVO mvo = null;	// mvo 객체 생성
		Statement st = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MEMBER "+
					"WHERE ID = " + id;		// ID = id 인 MemberVO 받아옴
		try {
			st = dataSource.getConnection().createStatement();
			rs = st.executeQuery(sql);
			
			
			
			// rs의 id 값이 0 임
			if(rs.next()) {
				mvo = new MemberVO();
				mvo.setId(rs.getInt("id"));		// 쿼리로 받아들인 객체의 id를 mvo 객체에 set
				// 여기서 받아들이는 id 가 0인 문제 발생
				System.out.println("id : " + rs.getInt("id"));
				mvo.setPass(rs.getString("pass"));
				mvo.setName(rs.getString("name"));
				mvo.setRegidate(rs.getDate("regidate"));
				
//				mvo.builder().id(rs.getInt("id")).pass(rs.getString("pass"))
//												.name(rs.getString("name"))
//												.regidate(rs.getDate("regidate")).build();
			}
			rs.close();
			st.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return mvo;
	}
	
	public List<MemberVO> addMember(MemberVO memberVO){
		
		if(getMemberById(memberVO.getId()) != null) {	// id에 해당하는 memberVO가 존재할 시
			return null;
		}
		memberVO.setRegidate(new Date());
		
		String sql = "INSERT INTO MEMBER (pass,name) values (?,?)";
		//	memberVO.getPass() + "," + memberVO.getName() + ")";	
		
		try {
			PreparedStatement psmt = dataSource.getConnection().prepareStatement(sql);
			psmt.setString(1, memberVO.getPass());
			psmt.setString(2, memberVO.getName());
			psmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();			
			
		}
		
		List<MemberVO> list = new ArrayList();
		list = getAllMember();
		return list;
		
	}
	
	public int updateMembers(MemberVO memberVO) {
		MemberVO m = getMemberById(memberVO.getId());
		if(m == null)
			return 0;
		String sql = "UPDATE MEMBER " +
					" SET PASS = " + memberVO.getPass() + ", NAME = " + memberVO.getName() +
					" WHERE ID = " + memberVO.getId();
		
		try {
			Statement st = dataSource.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 1;
		
	}
	
	public int removeMember(Integer id) {
		String sql = "DELETE FROM MEMBER " +
					"WHERE ID = " + id;
		try {
			PreparedStatement psmt = dataSource.getConnection().prepareStatement(sql);
			int rs = psmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;		
	}
	
	public List<MemberVO> addMemberJSON(MemberVO memberVO){
		return addMember(memberVO);
	}
		
	

}
