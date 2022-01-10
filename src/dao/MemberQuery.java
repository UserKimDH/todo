package dao;

public class MemberQuery {
	public static final String LOGIN;
	public static final String DELETE_MEMBER;
	public static final String INSERT_MEMBER;
	public static final String SELECT_MEMBER_ID;
	
	// staitc의 값을 선언할 수 있는 블락
	static {
		LOGIN = "SELECT member_id memberId FROM member WHERE member_id=? AND member_pw =?";
		DELETE_MEMBER = "DELETE FROM member WHERE member_id=? AND member_pw=?";
		INSERT_MEMBER = "INSERT member(member_id,member_pw,create_date,update_date) VALUES(?,?,NOW(),NOW())";
		SELECT_MEMBER_ID = "SELECT COUNT(*) FROM member WHERE member_id=?";
	}
}
