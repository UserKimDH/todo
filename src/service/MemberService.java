package service;

import java.sql.Connection;
import java.sql.SQLException;

import commons.DBUtil;
import dao.MemberDao;
import dao.TodoDao;
import vo.Member;

public class MemberService {
	private MemberDao memberDao;
	private TodoDao todoDao;
	
	// 아이디 중복 검사
	public boolean checkMemberId(String memberId) {
		
		// 커넥션 설정
		Connection conn = DBUtil.getConnection("jdbc:mariadb://127.0.0.1:3306/todo", "root", "java1004");
		
		System.out.println("[debug] MemberService.checkMemberId(String memberId) => 중복 검사할 아이디 : " + memberId);
		
		try {
			
			// 회원가입 실행
			memberDao = new MemberDao();
			int confirm = memberDao.selectMemberId(conn, memberId);
			
			if (confirm != 0) {
				System.out.println("[debug] MemberService.checkMemberId(String memberId) => 중복된 아이디");
				return false;
			}
			
		} catch (Exception e) {
			
			// 오류를 표기
			e.printStackTrace();
			System.out.println("[debug] MemberService.checkMemberId(String memberId) => 중복 검사 실패 : 오류 발생");
			
		} finally {
			try {
				
				// 최종적으로 트랜잭션 실행 여부에 상관없이, 커넥션(DB 자원)을 해제
				conn.close();
				
			} catch (SQLException se) {
				
				// 해제 실패 시 오류를 표기
				se.printStackTrace();
			}
		}

		// 실행이 성공했음을 반환
		System.out.println("[debug] MemberService.checkMemberId(String memberId) => 중복되는 아이디 없음");
		
		return true;
	}
	
	// 회원 가입(트랜잭션이 따로 구분되지 않는, 한 개의 작업만 수행한다 여기기에 오토 커밋 설정을 바꾸지 않음)
	public boolean addMember(String memberId, String memberPw) {
		// 커넥션 설정
		Connection conn = DBUtil.getConnection("jdbc:mariadb://127.0.0.1:3306/todo", "root", "java1004");
		
		System.out.println("[debug] MemberService.addMember(String memberId, String memberPw) => 가입할 회원 아이디 : " + memberId);
		System.out.println("[debug] MemberService.addMember(String memberId, String memberPw) => 가입할 회원 비밀번호 : " + memberPw);
		
		try {
			
			// 회원가입 실행
			memberDao = new MemberDao();
			int confirm = memberDao.insertMember(conn, memberId, memberPw);
			
			if (confirm != 1) {
				System.out.println("[debug] MemberService.addMember(String memberId, String memberPw) => 회원 가입 실패 : 등록 안됨");
				return false;
			}
			
		} catch (Exception e) {
			
			// 오류를 표기
			e.printStackTrace();
			System.out.println("[debug] MemberService.addMember(String memberId, String memberPw) => 회원 가입 실패 : 오류 발생");
			
		} finally {
			try {
				
				// 최종적으로 트랜잭션 실행 여부에 상관없이, 커넥션(DB 자원)을 해제
				conn.close();
				
			} catch (SQLException se) {
				
				// 해제 실패 시 오류를 표기
				se.printStackTrace();
			}
		}

		// 실행이 성공했음을 반환
		System.out.println("[debug] MemberService.addMember(String memberId, String memberPw) => 회원 가입 성공");
		
		return true;
	}
	
	// 회원 탈퇴
	public boolean removeMember(String memberId, String memberPw) {
		
		// 커넥션 설정
		Connection conn = DBUtil.getConnection("jdbc:mariadb://127.0.0.1:3306/todo", "root", "java1004");
		try {
			
			// 트랜잭션 적용을 위해 오토 커밋 false 설정
			// 트랜잭션은 T1, T2, T3, ..., Tn 등의 기호를 사용하여 표현
			conn.setAutoCommit(false);
			
			// T1. 회원탈퇴하려는 회원이 작성한 todo 모두 삭제
			todoDao = new TodoDao();
			todoDao.deleteTodo(conn, memberId);
			
			// T2. 회원탈퇴하려는 회원의 회원 정보 삭제
			memberDao = new MemberDao();
			int confirm = memberDao.deleteMember(conn, memberId, memberPw);
			
			// 만약 T2의 결과가 0이면 비밀번호를 잘못 입력한 것이므로 지금까지의 변경 사항이 롤백되도록 오류를 강제 발생
			if (confirm == 0) {
				throw new Exception("입력하신 비밀번호와 일치하는 회원정보가 없습니다."); 
			}
			
			// 트랜잭션이 모두 성공적으로 싫행된다면, 해당 변경 내용을 커밋
			System.out.println("[debug] MemberService.removeMember(String memberId, String memberPw) => 회원 탈퇴 성공");
			conn.commit();
			
		} catch (Exception e) {
			
			// 오류를 표기
			e.printStackTrace();
			
			// 트랜잭션 실행 중, 오류 발생 시, 진행된 변경 내용들을 모두 초기화
			// 실행이 실패했음을 반환
			try {
				System.out.println("[debug] MemberService.removeMember(String memberId, String memberPw) => 회원 탈퇴 실패");
				conn.rollback();
				return false;
			} catch(Exception re) {
				
				// 초기화 실패 시 오류를 표기
				re.printStackTrace();
			}
			
		} finally {
			try {
				
				// 최종적으로 트랜잭션 실행 여부에 상관없이, 커넥션(DB 자원)을 해제
				conn.close();
			} catch (SQLException se) {
				
				// 해제 실패 시 오류를 표기
				se.printStackTrace();
			}
		}
		
		// 실행이 성공했음을 반환
		return true;
	}
	
	// 로그인
	public Member login(Member member) {
		Member loginMember = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://127.0.0.1:3306/todo", "root", "java1004");
			memberDao = new MemberDao();
			loginMember = memberDao.login(conn, member);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return loginMember;
	}
}
