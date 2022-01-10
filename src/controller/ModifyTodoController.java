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


@WebServlet("/member/modifyTodo")
public class ModifyTodoController extends HttpServlet {
	private TodoService todoService;
	
	// 일정 수정 폼으로 이동
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 수정할 일정의 정보를 입력받음
		String todoDate = request.getParameter("todoDate");
		String memberId = request.getParameter("memberId");
		String todoNo = request.getParameter("todoNo");
		String todoContent = request.getParameter("todoContent");
		
		
		Todo todo = new Todo();
		todo.setTodoDate(todoDate);
		todo.setMemberId(memberId);
		
		// 수정폼에서도 일정 목록을 볼 수 있도록 일정 목록을 불러옴
		todoService = new TodoService();
		List<Todo> todoList = todoService.getTodoListByDate(todo);

		// 수정에 필요한 데이터들을 전달
		request.setAttribute("todoList", todoList);
		request.setAttribute("todoDate", todoDate);
		request.setAttribute("todoNo", todoNo);
		request.setAttribute("todoContent", todoContent);
		request.setAttribute("memberId", memberId);
		
		// 일정 수정 폼으로 이동
		request.getRequestDispatcher("/WEB-INF/view/updateTodo.jsp").forward(request, response);
		
	}

	// 일정 수정
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 수정할 일정 정보를 입력받음
		String todoDate = request.getParameter("todoDate");
		String memberId = request.getParameter("memberId");
		int todoNo = Integer.parseInt(request.getParameter("todoNo"));
		String todoContent = request.getParameter("todoContent");
		
		Todo todo = new Todo();
		
		// 수정할 일정 정보를 세팅
		todo.setTodoNo(todoNo);
		todo.setTodoDate(todoDate);
		todo.setMemberId(memberId);
		todo.setTodoContent(todoContent);
		
		// 일정 수정
		todoService = new TodoService();
		int confirm = todoService.modifyTodoByDate(todo);
		
		// 일정 수정 결과에 따라 해당하는 안내문과 페이지 이동
		if (confirm==0) {
			System.out.println("[debug] UpdateTodoController => 일정 변경 실패");
			response.sendRedirect(request.getContextPath()+"/member/todoList?todoDate=" + todoDate);
		} else {
			System.out.println("[debug] UpdateTodoController => 일정 변경 성공");
			response.sendRedirect(request.getContextPath()+"/member/todoList?todoDate=" + todoDate);
		}
	
	}

}
