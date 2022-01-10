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
	
	// �븘�씠�뵒 以묐났 寃��궗
	public boolean checkMemberId(String memberId) {
		
		// 而ㅻ꽖�뀡 �꽕�젙
		Connection conn = DBUtil.getConnection("jdbc:mariadb://13.125.218.194:3306/todo", "root", "java1004");
		
		System.out.println("[debug] MemberService.checkMemberId(String memberId) => 以묐났 寃��궗�븷 �븘�씠�뵒 : " + memberId);
		
		try {
			
			// �쉶�썝媛��엯 �떎�뻾
			memberDao = new MemberDao();
			int confirm = memberDao.selectMemberId(conn, memberId);
			
			if (confirm != 0) {
				System.out.println("[debug] MemberService.checkMemberId(String memberId) => 以묐났�맂 �븘�씠�뵒");
				return false;
			}
			
		} catch (Exception e) {
			
			// �삤瑜섎�� �몴湲�
			e.printStackTrace();
			System.out.println("[debug] MemberService.checkMemberId(String memberId) => 以묐났 寃��궗 �떎�뙣 : �삤瑜� 諛쒖깮");
			
		} finally {
			try {
				
				// 理쒖쥌�쟻�쑝濡� �듃�옖�옲�뀡 �떎�뻾 �뿬遺��뿉 �긽愿��뾾�씠, 而ㅻ꽖�뀡(DB �옄�썝)�쓣 �빐�젣
				conn.close();
				
			} catch (SQLException se) {
				
				// �빐�젣 �떎�뙣 �떆 �삤瑜섎�� �몴湲�
				se.printStackTrace();
			}
		}

		// �떎�뻾�씠 �꽦怨듯뻽�쓬�쓣 諛섑솚
		System.out.println("[debug] MemberService.checkMemberId(String memberId) => 以묐났�릺�뒗 �븘�씠�뵒 �뾾�쓬");
		
		return true;
	}
	
	// �쉶�썝 媛��엯(�듃�옖�옲�뀡�씠 �뵲濡� 援щ텇�릺吏� �븡�뒗, �븳 媛쒖쓽 �옉�뾽留� �닔�뻾�븳�떎 �뿬湲곌린�뿉 �삤�넗 而ㅻ컠 �꽕�젙�쓣 諛붽씀吏� �븡�쓬)
	public boolean addMember(String memberId, String memberPw) {
		// 而ㅻ꽖�뀡 �꽕�젙
		Connection conn = DBUtil.getConnection("jdbc:mariadb://13.125.218.194:3306/todo", "root", "java1004");
		
		System.out.println("[debug] MemberService.addMember(String memberId, String memberPw) => 媛��엯�븷 �쉶�썝 �븘�씠�뵒 : " + memberId);
		System.out.println("[debug] MemberService.addMember(String memberId, String memberPw) => 媛��엯�븷 �쉶�썝 鍮꾨�踰덊샇 : " + memberPw);
		
		try {
			
			// �쉶�썝媛��엯 �떎�뻾
			memberDao = new MemberDao();
			int confirm = memberDao.insertMember(conn, memberId, memberPw);
			
			if (confirm != 1) {
				System.out.println("[debug] MemberService.addMember(String memberId, String memberPw) => �쉶�썝 媛��엯 �떎�뙣 : �벑濡� �븞�맖");
				return false;
			}
			
		} catch (Exception e) {
			
			// �삤瑜섎�� �몴湲�
			e.printStackTrace();
			System.out.println("[debug] MemberService.addMember(String memberId, String memberPw) => �쉶�썝 媛��엯 �떎�뙣 : �삤瑜� 諛쒖깮");
			
		} finally {
			try {
				
				// 理쒖쥌�쟻�쑝濡� �듃�옖�옲�뀡 �떎�뻾 �뿬遺��뿉 �긽愿��뾾�씠, 而ㅻ꽖�뀡(DB �옄�썝)�쓣 �빐�젣
				conn.close();
				
			} catch (SQLException se) {
				
				// �빐�젣 �떎�뙣 �떆 �삤瑜섎�� �몴湲�
				se.printStackTrace();
			}
		}

		// �떎�뻾�씠 �꽦怨듯뻽�쓬�쓣 諛섑솚
		System.out.println("[debug] MemberService.addMember(String memberId, String memberPw) => �쉶�썝 媛��엯 �꽦怨�");
		
		return true;
	}
	
	// �쉶�썝 �깉�눜
	public boolean removeMember(String memberId, String memberPw) {
		
		// 而ㅻ꽖�뀡 �꽕�젙
		Connection conn = DBUtil.getConnection("jdbc:mariadb://13.125.218.194:3306/todo", "root", "java1004");
		try {
			
			// �듃�옖�옲�뀡 �쟻�슜�쓣 �쐞�빐 �삤�넗 而ㅻ컠 false �꽕�젙
			// �듃�옖�옲�뀡�� T1, T2, T3, ..., Tn �벑�쓽 湲고샇瑜� �궗�슜�븯�뿬 �몴�쁽
			conn.setAutoCommit(false);
			
			// T1. �쉶�썝�깉�눜�븯�젮�뒗 �쉶�썝�씠 �옉�꽦�븳 todo 紐⑤몢 �궘�젣
			todoDao = new TodoDao();
			todoDao.deleteTodo(conn, memberId);
			
			// T2. �쉶�썝�깉�눜�븯�젮�뒗 �쉶�썝�쓽 �쉶�썝 �젙蹂� �궘�젣
			memberDao = new MemberDao();
			int confirm = memberDao.deleteMember(conn, memberId, memberPw);
			
			// 留뚯빟 T2�쓽 寃곌낵媛� 0�씠硫� 鍮꾨�踰덊샇瑜� �옒紐� �엯�젰�븳 寃껋씠誘�濡� 吏�湲덇퉴吏��쓽 蹂�寃� �궗�빆�씠 濡ㅻ갚�릺�룄濡� �삤瑜섎�� 媛뺤젣 諛쒖깮
			if (confirm == 0) {
				throw new Exception("�엯�젰�븯�떊 鍮꾨�踰덊샇�� �씪移섑븯�뒗 �쉶�썝�젙蹂닿� �뾾�뒿�땲�떎."); 
			}
			
			// �듃�옖�옲�뀡�씠 紐⑤몢 �꽦怨듭쟻�쑝濡� �떕�뻾�맂�떎硫�, �빐�떦 蹂�寃� �궡�슜�쓣 而ㅻ컠
			System.out.println("[debug] MemberService.removeMember(String memberId, String memberPw) => �쉶�썝 �깉�눜 �꽦怨�");
			conn.commit();
			
		} catch (Exception e) {
			
			// �삤瑜섎�� �몴湲�
			e.printStackTrace();
			
			// �듃�옖�옲�뀡 �떎�뻾 以�, �삤瑜� 諛쒖깮 �떆, 吏꾪뻾�맂 蹂�寃� �궡�슜�뱾�쓣 紐⑤몢 珥덇린�솕
			// �떎�뻾�씠 �떎�뙣�뻽�쓬�쓣 諛섑솚
			try {
				System.out.println("[debug] MemberService.removeMember(String memberId, String memberPw) => �쉶�썝 �깉�눜 �떎�뙣");
				conn.rollback();
				return false;
			} catch(Exception re) {
				
				// 珥덇린�솕 �떎�뙣 �떆 �삤瑜섎�� �몴湲�
				re.printStackTrace();
			}
			
		} finally {
			try {
				
				// 理쒖쥌�쟻�쑝濡� �듃�옖�옲�뀡 �떎�뻾 �뿬遺��뿉 �긽愿��뾾�씠, 而ㅻ꽖�뀡(DB �옄�썝)�쓣 �빐�젣
				conn.close();
			} catch (SQLException se) {
				
				// �빐�젣 �떎�뙣 �떆 �삤瑜섎�� �몴湲�
				se.printStackTrace();
			}
		}
		
		// �떎�뻾�씠 �꽦怨듯뻽�쓬�쓣 諛섑솚
		return true;
	}
	
	// 濡쒓렇�씤
	public Member login(Member member) {
		Member loginMember = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://13.125.218.194:3306/todo", "root", "java1004");
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
