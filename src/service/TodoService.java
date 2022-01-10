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
	
	// �씪�젙 紐⑸줉 議고쉶
	public List<Todo> getTodoListByDate(Todo todo) {
		
		System.out.println("[debug] TodoService. getTodoListByDate(Todo todo) => �긽�꽭蹂닿린�븷 �씪�젙 �젙蹂� : " + todo.toString());
		
		List<Todo> todoList = null; new ArrayList<Todo>();
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://13.125.218.194:3306/todo", "root", "java1004");
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
	
	// �씪�젙 �벑濡�
	public int addTodoByDate(Todo todo) {
		
		System.out.println("[debug] TodoService.addTodoByDate(Todo todo) => �벑濡앺븷 �씪�젙 �젙蹂� : " + todo.toString());
		
		Connection conn = null;
		int confirm = 0;
		
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://13.125.218.194:3306/todo", "root", "java1004");
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
	
	// �씪�젙 蹂�寃�
	public int modifyTodoByDate(Todo todo) {
		
		System.out.println("[debug] TodoService.modifyTodoByDate(Todo todo) => �닔�젙�븷 �씪�젙 �젙蹂� : " + todo.toString());
		
		Connection conn = null;
		int confirm = 0;
		
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://13.125.218.194:3306/todo", "root", "java1004");
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
	
	// �듅�젙 �씪�젙 �궘�젣(1媛쒖쓽 �씪�젙)
	public int removeTodoByDate(Todo todo) {
		
		System.out.println("[debug] TodoService.removeTodoByDate(Todo todo) => �궘�젣�븷 �씪�젙 �젙蹂� : " + todo.toString());
		
		Connection conn = null;
		int confirm = 0;
		
		try {
			
			conn = DBUtil.getConnection("jdbc:mariadb://13.125.218.194:3306/todo", "root", "java1004");
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
