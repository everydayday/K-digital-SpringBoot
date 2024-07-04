package edu.pnu.service;

import java.util.List;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberVO;

public class MemberService {

	private MemberDao memberDao;
	
	
	public MemberService() {	
		
		memberDao = new MemberDao();
	}
	
	public List<MemberVO> getAllMember(){
		return memberDao.getAllMember();
	}
	
	public MemberVO getMember(Integer id) {
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
