package vo;

public class Todo {
	private int todoNo;
	private String memberId; // 고민 : private Member member 했을때?
	private String todoDate;
	private String todoContent;
	private String createDate;
	private String updateDate;
	public int getTodoNo() {
		return todoNo;
	}
	public void setTodoNo(int todoNo) {
		this.todoNo = todoNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getTodoDate() {
		return todoDate;
	}
	public void setTodoDate(String todoDate) {
		this.todoDate = todoDate;
	}
	public String getTodoContent() {
		return todoContent;
	}
	public void setTodoContent(String todContent) {
		this.todoContent = todContent;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "Todo [todoNo=" + todoNo + ", memberId=" + memberId + ", todoDate=" + todoDate + ", todoContent="
				+ todoContent + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
}
