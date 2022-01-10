package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TodoService;
import vo.Member;
import vo.Todo;


@WebServlet("/member/todoList")
public class TodoController extends HttpServlet {
	private TodoService todoService;
	
	// 일정 목록 조회
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String todoDate = null;
		
		// 일정표에서 바로 이동하거나(y,m,d 데이터 존재), 다른 페이지 혹은 메소드의 로직에 따라 이동하거나(todoDate 데이터 존재)에 따라 다르게 todoDate 값을 세팅  
		if ( request.getParameter("todoDate")==null || request.getParameter("todoDate").equals("") ) {
			String y = request.getParameter("y");
			String m = request.getParameter("m");
			String d = request.getParameter("d");
			
			if (d.length() <2) {
				d = "0" + d;
			}
			
			todoDate = y + "-" + m + "-" + d;
			
		} else {
			
			todoDate = request.getParameter("todoDate");
			
		}
		
		String memberId = ((Member)request.getSession().getAttribute("loginMember")).getMemberId();
		
		Todo todo = new Todo();
		todo.setTodoDate(todoDate);
		todo.setMemberId(memberId);
		
		// 일정 목록 조회
		todoService = new TodoService();
		List<Todo> todoList = todoService.getTodoListByDate(todo);
		
		// 일정 목록 세팅 및 페이지 이동
		request.setAttribute("todoList", todoList);
		request.setAttribute("todoDate", todoDate);
		request.getRequestDispatcher("/WEB-INF/view/todoList.jsp").forward(request, response);
		
	}

	// 같은 페이지에서 삽입과 조회가 이루어졌기에, 각각 get, post만을 사용하기도 해서 같은 컨트롤러 내에서 조회와 삽입을 구현
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 등록할 일정 정보를 입력받음
		String memberId = request.getParameter("memberId");
		String todoDate = request.getParameter("todoDate");
		String todoContent = request.getParameter("todoContent");
		
		Todo todo = new Todo();
		
		// 등록할 일정 정보 세팅
		todo.setTodoDate(todoDate);
		todo.setMemberId(memberId);
		todo.setTodoContent(todoContent);
		
		// 일정 등록
		todoService = new TodoService();
		int confirm = todoService.addTodoByDate(todo);
		
		// 일정 등록 결과에 따라 해당하는 안내문과 페이지 이동
		if (confirm==0) {
			System.out.println("[debug] TodoController => 일정 등록 실패");
			response.sendRedirect(request.getContextPath()+"/member/todoList?todoDate=" + todoDate);
		} else {
			System.out.println("[debug] TodoController => 일정 등록 성공");
			response.sendRedirect(request.getContextPath()+"/member/todoList?todoDate=" + todoDate);
		}
	
	}

}
