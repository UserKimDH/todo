package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import commons.DBUtil;
import dao.TodoDao;
import vo.Todo;

public class TodoService {
	private TodoDao todoDao;
	
	// 일정 목록 조회
	public List<Todo> getTodoListByDate(Todo todo) {
		
		System.out.println("[debug] TodoService. getTodoListByDate(Todo todo) => 상세보기할 일정 정보 : " + todo.toString());
		
		List<Todo> todoList = null; new ArrayList<Todo>();
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://127.0.0.1:3306/todo", "root", "java1004");
			todoDao = new TodoDao();
			todoList = todoDao.selectTodoList(conn, todo);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return todoList;
	}
	
	// 일정 등록
	public int addTodoByDate(Todo todo) {
		
		System.out.println("[debug] TodoService.addTodoByDate(Todo todo) => 등록할 일정 정보 : " + todo.toString());
		
		Connection conn = null;
		int confirm = 0;
		
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://127.0.0.1:3306/todo", "root", "java1004");
			todoDao = new TodoDao();
			confirm = todoDao.insertTodo(conn, todo);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return confirm;
	}
	
	// 일정 변경
	public int modifyTodoByDate(Todo todo) {
		
		System.out.println("[debug] TodoService.modifyTodoByDate(Todo todo) => 수정할 일정 정보 : " + todo.toString());
		
		Connection conn = null;
		int confirm = 0;
		
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://127.0.0.1:3306/todo", "root", "java1004");
			todoDao = new TodoDao();
			confirm = todoDao.updateTodo(conn, todo);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return confirm;
	}
	
	// 특정 일정 삭제(1개의 일정)
	public int removeTodoByDate(Todo todo) {
		
		System.out.println("[debug] TodoService.removeTodoByDate(Todo todo) => 삭제할 일정 정보 : " + todo.toString());
		
		Connection conn = null;
		int confirm = 0;
		
		try {
			
			conn = DBUtil.getConnection("jdbc:mariadb://127.0.0.1:3306/todo", "root", "java1004");
			todoDao = new TodoDao();
			confirm = todoDao.deleteTodoOne(conn, todo);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return confirm;
	}
}
