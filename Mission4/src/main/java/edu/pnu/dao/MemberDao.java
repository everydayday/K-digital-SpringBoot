package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.pnu.domain.MemberVO;

public class MemberDao {
	
	private Connection con;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	public MemberDao() {
		
		try {			
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/mission3","sa","abcd");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Map<String,Object> getAllMember() {
		HashMap<String, Object>all_map = new HashMap<String, Object>(); 
		List<MemberVO> list = new ArrayList<MemberVO>(); 
		Statement st = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MEMBER";
		boolean flag = true;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				MemberVO m = MemberVO.builder().id(rs.getInt("id"))
												.name(rs.getString("name"))
												.pass(rs.getString("pass"))
												.regidate(rs.getDate("regidate"))
												.build();
				list.add(m);
				
			}
		
		}catch(Exception e){
			flag = false;
			e.printStackTrace();
		} finally {			
//			if(psmt != null) psmt.close();
//			if(rs != null) rs.close(); 
		}	
		
		all_map.put("memberVO", list);
		all_map.put("sqlstring", sql);
		all_map.put("success", flag);
		return all_map;		
	}
	
	public  Map<String,Object> getMember() {
		return null;
	}
	
	public Map<String,Object> getMemberById(Integer id) {
		
		HashMap<String, Object>all_map = new HashMap<String, Object>(); 
		MemberVO memberVO = new MemberVO(); 
		Statement st = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MEMBER "
				+ "WHERE ID = " + id;
		boolean flag = true;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				memberVO.builder().id(rs.getInt("id"))
												.name(rs.getString("name"))
												.pass(rs.getString("pass"))
												.regidate(rs.getDate("regidate"))
												.build();				
			}
		
		}catch(Exception e){
			flag = false;
			e.printStackTrace();
		} finally {			

		}	
		
		all_map.put("memberVO", memberVO);
		all_map.put("sqlstring", sql);
		all_map.put("success", flag);
		return all_map;	
		
		
		/*
		MemberVO mvo = null;	// mvo 객체 생성
		String sql = "SELECT * FROM MEMBER "+
					"WHERE ID = " + id;		// ID = id 인 MemberVO 받아옴
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			
			
			while(rs.next()) {
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
			psmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return mvo;
		*/
	}
	
	public List<MemberVO> addMember(MemberVO memberVO){
		
		if(getMemberById(memberVO.getId()) != null) {	// id에 해당하는 memberVO가 존재할 시
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
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		list = (List<MemberVO>)getAllMember().get("memberVO");
		return list;
		
	}
	
	public int updateMembers(MemberVO memberVO) {
		MemberVO m = (MemberVO)getMemberById(memberVO.getId()).get("memberVO");
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
