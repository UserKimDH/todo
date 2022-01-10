package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/logout")
public class LogoutController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* LoginFilter 사용
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) { // 이미 로그인 되어있는 상태다
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		*/
		
		request.getSession().invalidate();
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}

}
