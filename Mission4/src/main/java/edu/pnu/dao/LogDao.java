package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.pnu.domain.LogVO;

public class LogDao {
	private Connection con;
	private PreparedStatement psmt;
	private ResultSet rs;
	private LogVO logVO;
	
	public LogDao() {
		
		
		String url = "jdbc:mysql://localhost:3306/mysql";
		String username = "musthave";
		String password = "tiger";
		
		
		try {
			con = DriverManager.getConnection(url, username, password);
					
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public void setLog(String method, String sqlstring, boolean success) {
		String sql =  "insert into dblog (method, sqlstring, success) "
				+ " values (?, ?, ?)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, method);
			psmt.setString(2, sqlstring);
			psmt.setBoolean(3, success);
			
			rs = psmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public LogVO getLogById(int id) {
		String sql = "SELECT * FROM DBLOG "
				+ "WHERE ID = " + id;
		// 존재 하지 않는 id 값을 호출 할 시 null 값 호출됨 
		LogVO logVO = new LogVO();
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			logVO.builder().id(rs.getInt("id")).method(rs.getString("method"))
							.sqlstring(rs.getString("sqlstring")).regidate(rs.getDate("regidate"))
							.success(rs.getBoolean("success")).build();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return logVO;
		
		
		
		
	}
	
	
	
	
	
	
	
}
