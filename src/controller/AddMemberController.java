package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import service.MemberService;
import service.TodoService;
import vo.Member;
import vo.Todo;

/**
 * Servlet implementation class AddMemberController
 */
@WebServlet("/addMember")
public class AddMemberController extends HttpServlet {
	private MemberService memberService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 회원가입 페이지로 이동
		request.getRequestDispatcher("/WEB-INF/view/addMember.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원가입 정보를 입력받음
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");

		// 회원 가입
		memberService = new MemberService();
		boolean confirm = memberService.addMember(memberId, memberPw);
		
		// 회원 가입 결과에 따라 해당하는 안내문과 페이지 이동
		if (confirm==false) {
			System.out.println("[debug] AddMemberController => 회원 가입 실패");
			response.sendRedirect(request.getContextPath()+"/addMember");
		} else {
			System.out.println("[debug] AddMemberController => 회원 가입 성공");
			response.sendRedirect(request.getContextPath()+"/login");
		}
	}

}
