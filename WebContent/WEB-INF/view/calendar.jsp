<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>자바</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
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
		tbody > tr :hover {
			background: #e6ffff;
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
		<h2>일정 스케줄링 서비스</h2>
		<p>날짜를 선택하여 일정을 관리하고 효율적인 삶을 개척하세요.</p>
		<p>이달의 일정 수: ${todoList.size()} </p>
	</div>
	<div class="container p-3 align-items-center" id="mainContent">
		<table class="table table-bordered" style="width: 100%">
			<thead>
				<tr class="align-items-center text-center">
					<td class="text-center pageButton h4" width="15%">
						<a href="${pageContext.request.contextPath}/member/calendar?currentYear=${targetYear}&currentMonth=${targetMonth}&option=pre">&lt;이전 달</a>
					</td>
					<td class="text-center h3" colspan="5" width="70%" >
						${targetYear}년 ${targetMonth}월 일정 목록
					</td>
					<td class="text-center pageButton h4" width="15%">
						<a href="${pageContext.request.contextPath}/member/calendar?currentYear=${targetYear}&currentMonth=${targetMonth}&option=next">다음 달&gt;</a>
					</td>
				</tr>
				<tr class="text-center" id="week">
					<td style="color: red">일</td><td>월</td><td>화</td><td>수</td><td>목</td><td>금</td><td style="color: blue">토</td>
				</tr>
			</thead>
			<tbody>
				<tr class="text-center">
					<c:forEach var="i" begin="1" end="${startBlank+endDay+endBlank}" step="1">
						<c:if test="${i-startBlank >= 1 && i-startBlank<=endDay}">
							<td>
								<a href="${pageContext.request.contextPath}/member/todoList?y=${targetYear}&m=${targetMonth}&d=${i-startBlank}">${i-startBlank}</a>
								<div>
									<c:forEach var="todo" items="${todoList}">
										<c:if test="${i-startBlank == todo.todoDate.substring(8)}">
											<div>
												<a href="${pageContext.request.contextPath}/member/todoList?y=${targetYear}&m=${targetMonth}&d=${i-startBlank}">${todo.todoContent}...</a>
											</div>
										</c:if>
									</c:forEach>
								</div>
							</td>
						</c:if>
						<c:if test="${i-startBlank < 1 || i-startBlank>endDay}">
							<td>&nbsp;</td>
						</c:if>
						<c:if test="${i%7 == 0}">
							</tr><tr class="text-center">
						</c:if>
					</c:forEach>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>