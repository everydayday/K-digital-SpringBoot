package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
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
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class MemberDao {
	
	@Autowired
	private final DataSource dataSource;
	
	private Connection con;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	public MemberDao() {
		System.out.println("Dao 생성");
		try {			
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/mission3","sa","abcd");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<MemberVO> getAllMember() {
		List<MemberVO> list = new ArrayList(); 
		Statement st = null;
		ResultSet rs = null;
		try {
			st = dataSource.createStatement();
//			psmt = con.prepareStatement("SELECT * FROM MEMBER");
			rs = st.executeQuery("SELECT * FROM MEMBER");
			
			while(rs.next()) {
				MemberVO m = MemberVO.builder().id(rs.getInt("id"))
												.name(rs.getString("name"))
												.pass(rs.getString("pass"))
												.regidate(rs.getDate("regidate"))
												.build();
				list.add(m);
//				
//				mvo.setId(rs.getInt("id"));
//				mvo.setPass(rs.getString("pass"));
//				mvo.setName(rs.getString("name"));
//				mvo.setRegidate(rs.getDate("regidate"));
				
			}
		
		}catch(Exception e){
			e.printStackTrace();
		} finally {			
//			if(psmt != null) psmt.close();
//			if(rs != null) rs.close(); 
		}		
		return list;		
	}
	
	public MemberVO getMember(Integer id) {
		return null;
	}
	
	public MemberVO getMemberById(Integer id) {
		
		MemberVO mvo = new MemberVO();
		String sql = "SELECT * FROM MEMBER "+
					"WHERE ID = " + id;
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			
			
			while(rs.next()) {
				mvo.setId(rs.getInt("id"));
				// 여기서 받아들이는 id 가 0인 문제 발생
				mvo.setPass(rs.getString("pass"));
				mvo.setName(rs.getString("name"));
				mvo.setRegidate(rs.getDate("regidate"));
			}
			rs.close();
			psmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(mvo.getId());
		return mvo;
	}
	
	public List<MemberVO> addMember(MemberVO memberVO){
		
		if(getMemberById(memberVO.getId()) != null) {
//			System.out.println(getMemberById(memberVO.getId()).getId());
			return null;
		}
		memberVO.setRegidate(new Date());
		
		String sql = "INSERT INTO MEMBER (pass,name) values (?,?)";
		//	memberVO.getPass() + "," + memberVO.getName() + ")";	
		
		try {
			psmt = con.prepareStatement(sql);
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
					"SET PASS = " + memberVO.getPass() + ", NAME = " + memberVO.getName() +
					"WHERE ID = " + memberVO.getId();
		
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			con.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 1;
		
	}
	
	public int removeMember(Integer id) {
		String sql = "DELETE FROM MEMBER" +
					"WHERE ID = " + id;
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
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
