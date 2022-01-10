package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.Todo;

public class TodoDao {
	
	  public List<Todo> selectTodoListByMonth(Connection conn, Todo todo) throws SQLException {
	      List<Todo> list = new ArrayList<Todo>();
	      String sql = TodoQuery.SELECT_TODO_LIST_BY_MONTH;
	      PreparedStatement stmt = conn.prepareStatement(sql);
	      stmt.setString(1, todo.getMemberId());
	      stmt.setString(2, todo.getTodoDate().substring(0,7));
	      ResultSet rs = stmt.executeQuery();
	      while(rs.next()) {
	         Todo t = new Todo();
	         t.setTodoDate(rs.getString("todoDate"));
	         t.setTodoContent(rs.getString("todoContent"));
	         list.add(t);
	      }
	      return list;
	   }
	
	// 일정 등록
	public int insertTodo(Connection conn, Todo todo) throws SQLException {
		
		String sql = TodoQuery.INSERT_TODO;
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		// 파라미터 세팅
		stmt.setString(1, todo.getTodoDate());
		stmt.setString(2, todo.getTodoContent());
		stmt.setString(3, todo.getMemberId());
		
		System.out.println("[debug] TodoDao.insertTodo(Connection conn, Todo todo) => 쿼리문 : " + stmt);
		int confirm = stmt.executeUpdate();
		
		System.out.println("[debug] TodoDao.insertTodo(Connection conn, Todo todo) => 등록된 일정의 수 : " + confirm);
		
		stmt.close();
		
		return confirm;
	}
	
	// 일정 변경
	public int updateTodo(Connection conn, Todo todo) throws SQLException {
		
		String sql = TodoQuery.UPDATE_TODO;
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		// 파라미터 세팅
		stmt.setString(1, todo.getTodoContent());
		stmt.setInt(2, todo.getTodoNo());
		stmt.setString(3, todo.getMemberId());
		
		System.out.println("[debug] TodoDao.updateTodo(Connection conn, Todo todo) => 쿼리문 : " + stmt);
		int confirm = stmt.executeUpdate();
		
		System.out.println("[debug] TodoDao.updateTodo(Connection conn, Todo todo) => 수정된 일정의 수 : " + confirm);
		
		stmt.close();
		
		return confirm;
	}
	
	// 일정 목록 조회
	public List<Todo> selectTodoList(Connection conn, Todo todo) throws SQLException {
		
		List<Todo> todoList = new ArrayList<Todo>();
		
		String sql = TodoQuery.SELECT_TODOLIST_BY_DATE;
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		// 파라미터 세팅
		stmt.setString(1, todo.getTodoDate());
		stmt.setString(2, todo.getMemberId());
		
		System.out.println("[debug] TodoDao.selectTodoList(Connection conn, Todo todo) => 쿼리문 : " + stmt);
		ResultSet rs = stmt.executeQuery();
		
		// 디버깅용 변수(순번 확인)
		int index = 0;
		
		while(rs.next()) {
			
			index++;
			
			Todo t = new Todo();
			
			// 조회 결과 세팅
			t.setTodoNo(rs.getInt("todoNo"));
			t.setMemberId(rs.getString("memberId"));
			t.setTodoDate(rs.getString("todoDate"));
			t.setTodoContent(rs.getString("todoContent"));
			t.setCreateDate(rs.getString("createDate"));
			t.setUpdateDate(rs.getString("updateDate"));
			
			System.out.println("[debug] TodoDao.selectTodoList(Connection conn, Todo todo) => " + index + "번째 todo 내용: " + t.toString());
			
			todoList.add(t);
			
		}
		
		return todoList; 
	}
	
	// 일정 삭제
	public int deleteTodo(Connection conn, String memberId) throws SQLException {
		
		String sql = TodoQuery.DELETE_TODO;
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		// 파라미터 세팅
		stmt.setString(1, memberId);
		
		System.out.println("[debug] TodoDao.deleteTodo(Connection conn, String memberId) => 쿼리문 : " + stmt);
		
		int confirm = stmt.executeUpdate();
		
		System.out.println("[debug] TodoDao.deleteTodo(Connection conn, String memberId) => 삭제된 일정의 수 : " + confirm);
		
		stmt.close();
		
		return confirm;
	}
	
	// 특정 일정 삭제(1개의 일정)
	public int deleteTodoOne(Connection conn, Todo todo) throws SQLException {
		
		String sql = TodoQuery.DELETE_TODO_ONE;
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		// 파라미터 세팅
		stmt.setInt(1, todo.getTodoNo());
		stmt.setString(2, todo.getMemberId());
		
		System.out.println("[debug] TodoDao.deleteTodoOne(Connection conn, Todo todo) => 쿼리문 : " + stmt);
		
		int confirm = stmt.executeUpdate();
		
		System.out.println("[debug] TodoDao.deleteTodoOne(Connection conn, Todo todo) => 삭제된 일정의 수 : " + confirm);
		
		stmt.close();
		
		return confirm;
	}
	
}
