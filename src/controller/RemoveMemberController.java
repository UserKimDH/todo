package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;
import vo.Member;


@WebServlet("/member/removeMember")
public class RemoveMemberController extends HttpServlet {
	private MemberService memberService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 회원탈퇴 페이지로 이동
		request.getRequestDispatcher("/WEB-INF/view/remove.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 회원탈퇴 정보 입력받기
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		
		
		// 회원탈퇴 서비스 실행 후, 결과를 result 변수에 저장
		memberService = new MemberService();
		boolean result = memberService.removeMember(memberId, memberPw);
		
		if (result==true) {
			
			// 만약 회원탈퇴에 성공했다면, logout 서비스를 실행해주어, 해당 회원의 세션 정보도 제거
			System.out.println("[debug] RemoveController => 회원탈퇴 성공");
			response.sendRedirect(request.getContextPath()+"/member/logout");
			
		} else {
			
			// 만약 회원탈퇴에 실패했다면, 회원탈퇴 페이지로 다시 이동
			System.out.println("[debug] RemoveController => 회원탈퇴 실패");
			response.sendRedirect(request.getContextPath()+"/member/removeMember");
		}
		
	}

}
