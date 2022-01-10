package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TodoService;
import vo.Todo;


@WebServlet("/member/removeTodo")
public class RemoveTodoCntroller extends HttpServlet {
	private TodoService todoService;
	
	// 일정 삭제는 폼이 필요 없으므로 바로 일정 삭제를 해주도록 doPost를 사용
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	// 일정 삭제
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 삭제할 일정의 정보를 입력받음
		String memberId = request.getParameter("memberId");
		int todoNo = Integer.parseInt(request.getParameter("todoNo"));
		String todoDate = request.getParameter("todoDate");
		
		
		Todo todo = new Todo();
		todo.setTodoNo(todoNo);
		todo.setMemberId(memberId);
		
		// 일정 삭제
		todoService = new TodoService();
		int confirm = todoService.removeTodoByDate(todo);
		
		// 일정 삭제의 결과에 따라 해당하는 안내문과 페이지 이동
		if (confirm==0) {
			System.out.println("[debug] TodoController => 일정 삭제 실패");
			response.sendRedirect(request.getContextPath()+"/member/todoList?todoDate=" + todoDate);
		} else {
			System.out.println("[debug] TodoController => 일정 삭제 성공");
			response.sendRedirect(request.getContextPath()+"/member/todoList?todoDate=" + todoDate);
		}
	
	}
}
