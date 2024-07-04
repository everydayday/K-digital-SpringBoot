package edu.pnu.service;

import java.util.List;
import java.util.Map;

import edu.pnu.dao.LogDao;
import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberVO;

public class MemberService {

	private MemberDao memberDao;
	private LogDao logDao;
	
	public MemberService() {	
		memberDao = new MemberDao();
		logDao = new LogDao();
	}
	
	public List<MemberVO> getAllMember(){		
		Map<String,Object>list = memberDao.getAllMember();
		
		List<MemberVO>memberVO = (List<MemberVO>)list.get("memberVO");
		String sqlstring = (String)list.get("sqlstring");
		Boolean success = (Boolean)list.get("success");
		logDao.setLog("get", sqlstring, success);
		
		return memberVO;
	}
	
	public MemberVO getMember(Integer id) {
		Map<String,Object>list = memberDao.getMemberById(id);
		
		logDao.setLog("get", sqlstring, success);
		return memberDao.getMemberById(id);
	}
	
	public MemberVO getMemberById(Integer id) {
		return memberDao.getMemberById(id);
	}
	
	public void addMember(MemberVO memberVO) {
		
		memberDao.addMember(memberVO);
	}
	
	public int updateMembers(MemberVO memberVO) {
		return memberDao.updateMembers(memberVO);
	}
	
	public int removeMember(Integer id) {
		return memberDao.removeMember(id);
	}
	
	public void addMemberJSON(MemberVO memberVO) {
		memberDao.addMemberJSON(memberVO);
	}
}
