<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>자바</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<style type="text/css">
		a {
			text-decoration: none;
			color: grey;
		}
		tbody > tr {
			color: black;
		}
		tbody > tr > td {
			max-width: 40px;
		}
		tbody > tr > .manageButton :hover {
			color: blue;
		}
		.pageButton :hover {
			color: blue;
		}
		thead > tr {
			background: #f2f2f2;
		}
	</style>
</head>
<body>
	<ul class="nav justify-content-end">
		<li class="nav-item">
		  <a class="nav-link disabled" href="#">${loginMember.memberId}님 반갑습니다</a>
		</li>
		<li class="nav-item">
		  <a class="nav-link" href="${pageContext.request.contextPath}/member/logout">로그아웃</a>
		</li>
		<li class="nav-item">
		  <a class="nav-link" href="${pageContext.request.contextPath}/member/removeMember">회원탈퇴</a>
		</li>
	</ul>
	<div class="mt-4 p-5 text-black rounded" id="jumbo">
		<h2>${todoDate} 일정 목록</h2>
		<p>일정을 추가하고 수정하면서 효율적인 삶을 개척하세요.</p>
	</div>
	<div class="container p-3 align-items-center" id="mainContent">
		<table class="table table-bordered" style="width: 100%">
			<thead>
				<tr class="text-center" id="week">
					<td width="15%">일정날짜</td>
					<td width="40%">일정내용</td>
					<td width="15%">등록날짜</td>
					<td width="15%">수정날짜</td>
					<td width="15%" colspan="2">관리</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="todo" items="${todoList}">
					<tr>
						<td class="text-center align-middle">${todoDate}</td>
						<td class="text-left align-middle">${todo.todoContent}</td>
						<td class="text-center align-middle">${todo.createDate}</td>
						<td class="text-center align-middle">${todo.updateDate}</td>
						<td class="text-center align-middle manageButton"><a href="${pageContext.request.contextPath}/member/modifyTodo?todoNo=${todo.todoNo}&memberId=${todo.memberId}&todoDate=${todo.todoDate}&todoContent=${todo.todoContent}">수정</a></td>
						<td class="text-center align-middle manageButton"><a href="${pageContext.request.contextPath}/member/removeTodo?todoNo=${todo.todoNo}&memberId=${todo.memberId}&todoDate=${todo.todoDate}">삭제</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<form id="addTodoForm" method="post" action="${pageContext.request.contextPath}/member/todoList">
			<table class="table table-borderless" >
				<tr>
					<td>
						<input name="todoDate" type="hidden" value="${todoDate}">
						<input name="memberId" type="hidden" value="${loginMember.memberId}">
					</td>
				</tr>
				<tr>
					<td class="align-middle">
						<h3>일정 추가</h3>
						<div class="form-group"><textarea id="todoContent" name="todoContent" class="form-control" rows="10" cols="50" wrap="hard"></textarea></div>
						<div id="todoContentCheck">&nbsp;</div>
					</td>
				</tr>
			</table><br>
			<div class="text-center">
				<button type="button" id="addTodoButton" class="btn btn-outline-primary btn-sm">등록</button>
				<a href="<%=request.getContextPath() %>/member/calendar" class="btn btn-outline-info btn-sm">일정표</a>
			</div>
		</form>
	</div>
	<script>

	/* 유효성 검사 이벤트 */
	// 입력받아야 할 항목들의 값이 공백인지 아닌지, 아이디 중복 검사는 통과하였는지를 검사하고
	// 부족한 것이 있다면 이를 알려 이용자에게 정상적으로 값을 받을 수 있게끔 구현
	$('#addTodoButton').click(function() {

		// 일정 내용 검사
		if ($('#todoContent').val() == '') {
			
			$('#todoContentCheck').html($('<small style="color:red;">').text("일정내용을 입력해 주세요."));
		
		// 일정 추가 승인
		} else {
			
			// 일정 추가
			$('#addTodoForm').submit();
		}
		
	})
</script>
</body>
</html>