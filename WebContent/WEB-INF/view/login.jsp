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
			transform: translate(-50%, -45%);
			z-index: 1;
		}
		#intro{
			position: absolute;
			top: 20%;
			left: 50%;
			transform: translate(-50%, -50%);
			z-index: 1;
		}
	</style>
</head>
<body>
	<div class="container-fluid pt-3 text-center">
		<div class="text-center" style="padding-top: 50px" id="intro">
			<h4>스케줄링 서비스에 오신 것을 환영합니다.</h4>
			<p>서비스 이용을 위해 로그인 혹은 회원가입을 해주세요.</p>
		</div>
		<div class="container pt-3 center-block text-center">
			<div class="center-block text-center">
				<form id="loginForm" class="form-group" method="post"  action="${pageContext.request.contextPath}/login">
					<table>
						<tr>
							<td class="text-left align-middle" width="30%">아이디</td>
							<td><input class="form-control" type="text" name="memberId" id="memberId"></td>
						</tr>
						<tr>
							<td></td>
							<td id="memberIdCheck">&nbsp;</td>
						</tr>
						<tr>
							<td class="text-left align-middle" width="30%">비밀번호</td>
							<td><input class="form-control" type="password" name="memberPw" id="memberPw"></td>
						</tr>
						<tr>
							<td></td>
							<td id="memberPwCheck">&nbsp;</td>
						</tr>
					</table>
					<div class="text-center">
						<button type="button" id="loginButton" class="btn btn-outline-primary btn-sm">로그인</button>
					</div><br>
					<div>
						<a class="btn btn-outline-primary btn-sm" href="${pageContext.request.contextPath}/addMember">회원가입</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script>
	// %(() == jquery()
		$('#loginButton').click(function() {
			
			if ($('#memberId').val() == '') {
				$('#memberIdCheck').html($('<small style="color:red;">').text("아이디를 입력해 주세요."));
			} else if ($('#memberPw').val() == '') {
				// id를 입력했으므로 입력해 달라는 코멘트를 삭제
				$('#memberIdCheck').html("&nbsp;");
				
				$('#memberPwCheck').html($('<small style="color:red;">').text("비밀번호를 입력해 주세요."));
				
			} else {
				$('#loginForm').submit();
			}
			
		})
	</script>
</body>
</html>