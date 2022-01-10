package dao;

public class TodoQuery {
	
	public static final String DELETE_TODO;
	public static final String DELETE_TODO_ONE;
	public static final String SELECT_TODOLIST_BY_DATE;
	public static final String INSERT_TODO;
	public static final String UPDATE_TODO;
	public static final String SELECT_TODO_LIST_BY_MONTH;
	
	// 실질적 쿼리문 골격
	static {
		DELETE_TODO = "DELETE FROM todo WHERE member_id=?";
		SELECT_TODOLIST_BY_DATE = "SELECT todo_no todoNo, todo_date todoDate, member_id memberId ,todo_content todoContent, create_date createDate, update_date updateDate FROM todo WHERE todo_date=? AND member_id=?";
		INSERT_TODO = "INSERT todo(todo_date, todo_content, member_id, create_date, update_date) VALUES(?,?,?,NOW(),NOW())";
		UPDATE_TODO = "UPDATE todo SET todo_content=?, update_date=NOW() WHERE todo_no=? AND member_id=?";
		DELETE_TODO_ONE = "DELETE FROM todo WHERE todo_no=? AND member_id=? ";
		SELECT_TODO_LIST_BY_MONTH = "SELECT todo_date todoDate, SUBSTR(todo_content,1,5) todoContent FROM todo WHERE member_id=? AND SUBSTR(todo_date,1,7)=? ORDER BY todo_date ASC";

		
	}
}
