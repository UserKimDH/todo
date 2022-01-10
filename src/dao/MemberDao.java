package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.Member;

public class MemberDao {
	
	// 아이디 중복 여부를 반환
	public int selectMemberId (Connection conn, String memberId) throws SQLException {
		String sql = MemberQuery.SELECT_MEMBER_ID;
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, memberId);
		
		System.out.println("[debug] MemberDao.selectMemberId => 쿼리문 : " + stmt);
	
		ResultSet rs = stmt.executeQuery();
		
		int countMember = 0;
		if (rs.next()) {
			countMember = rs.getInt(1);	
		}
		
		System.out.println("[debug] MemberDao.selectMemberId => 중복되는 아이디 수 : " + countMember);
		
		stmt.close();
		rs.close();
		
		return countMember;
	}
	
	// 회원 가입
	public int insertMember(Connection conn, String memberId, String memberPw) throws SQLException {
		String sql = MemberQuery.INSERT_MEMBER;
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, memberId);
		stmt.setString(2, memberPw);
		
		System.out.println("[debug] MemberDao.insertMemeber => 쿼리문 : " + stmt);
		
		int confirm = stmt.executeUpdate();
		
		System.out.println("[debug] MemberDao.insertMemeber => 등록된 회원 수 : " + confirm);
		
		stmt.close();
		
		return confirm;
	}
	
	// 회원 삭제
	public int deleteMember(Connection conn, String memberId, String memberPw) throws SQLException {
		String sql = MemberQuery.DELETE_MEMBER;
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, memberId);
		stmt.setString(2, memberPw);
		
		System.out.println("[debug] MemberDao.deleteMemeber => 쿼리문 : " + stmt);
		
		int confirm = stmt.executeUpdate();
		
		System.out.println("[debug] MemberDao.deleteMemeber => 삭제된 회원 수 : " + confirm);
		
		stmt.close();
		
		return confirm;
	}
	
	// 로그인
	public Member login(Connection conn, Member paramMember) throws SQLException {
		Member loginMember = null;
		String sql = MemberQuery.LOGIN;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, paramMember.getMemberId());
		stmt.setString(2, paramMember.getMemberPw());
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			loginMember = new Member();
			loginMember.setMemberId(rs.getString("memberId"));
		}
		rs.close();
		stmt.close();
		return loginMember;
	}

}
