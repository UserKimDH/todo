<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>자바</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<style type="text/css">
		.center-block{
			position: absolute;
			top: 50%;
			left: 50%;
			transform: translate(-50%, -50%);
			z-index: 1;
		}
	</style>
</head>
<body>
	<div class="container-fluid pt-3">
		<div class="container pt-3 center-block" style="width:80%; padding:15px;">
			<div class="center-block text-center" >
				<h3>회원탈퇴</h3>
				<p>본인 확인을 위해 비밀번호를 입력해 주세요.</p>
				<form id="removeMemberForm" class="form-group" method="post" action="${pageContext.request.contextPath}/member/removeMember" >
					<table class="table table-borderless" >
						<tr>
							<td>
								<input class="form-control" type="text" id="memberPw" name="memberPw">
								<input class="form-control" type="hidden" name="memberId" value="${loginMember.memberId}">
								<div id="memberPwCheck">&nbsp;</div>
							</td>
						</tr>
					</table>
				</form>
				<div>
					<button id="removeMemberButton" class="btn btn-outline-danger text-center btn-sm" type="button">회원탈퇴</button>
				</div><br>
				<div>
					<a class="btn btn-outline-primary btn-sm" href="${pageContext.request.contextPath}/member/calendar">취소</a>
				</div>
				<script>
					
					/* 유효성 검사 이벤트 */
					// 입력받아야 할 항목들의 값이 공백인지 아닌지, 아이디 중복 검사는 통과하였는지를 검사하고
					// 부족한 것이 있다면 이를 알려 이용자에게 정상적으로 값을 받을 수 있게 끔 구현
 					$('#removeMemberButton').click(function() {
						
						// 비밀번호 검사
						if ($('#memberPw').val() == '') {
							
							$('#memberPwCheck').html($('<small style="color:red;">').text("비밀번호를 입력해 주세요."));
						
						// 모든 유효성 검사를 통과하였으니 비밀번호 수정 승인
						} else {
							
							$('#removeMemberForm').submit();
						}
					})
				</script>
			</div>
		</div>
	</div>
</body>
</html>