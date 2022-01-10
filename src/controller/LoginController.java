package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;
import vo.Member;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private MemberService memberService;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 이미 로그인 되었다면 요청 처리 불가
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) { // 이미 로그인 되어있는 상태다
			response.sendRedirect(request.getContextPath()+"/memer/calendar");
			return;
		}
		
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request.setCharacterEncoding("utf-8"); 
		// 모든 컨트롤러의 doPost() 상단에(요청처리전) 무조건 request.setCharacterEncoding()필요 
		// -> 공통된 로직이 중복 -> 필터를 사용하자
		
		// 이미 로그인 되었다면 요청 처리 불가 -> 이 로직도 필터를 사용 가능
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) { // 이미 로그인 되어있는 상태다
			response.sendRedirect(request.getContextPath()+"/member/calendar");
			return;
		}
		
		
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		Member paramMember = new Member();
		paramMember.setMemberId(memberId);
		paramMember.setMemberPw(memberPw);
		memberService = new MemberService();
		Member loginMember = memberService.login(paramMember);
		if(loginMember == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		session.setAttribute("loginMember", loginMember);
		response.sendRedirect(request.getContextPath()+"/member/calendar");
	}

}
