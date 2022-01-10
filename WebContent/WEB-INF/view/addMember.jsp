<%@page import="vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				<h1> 회원가입 </h1>
				<form id="signUpForm" class="form-group" method="post" action="${pageContext.request.contextPath}/addMember" >
					<table class="table table-borderless" >
						<tr>
							<td class="align-middle">
								아이디
								<div>&nbsp;</div>
							</td>
							<td>
								<input class="form-control" type="text" name="memberId" id="memberId">
								<div id="memberIdCheck" >&nbsp;</div>
							</td>
							<td>
								<button id="memberIdCheckButton" class="btn btn-outline-primary text-center" type="button">중복검사</button>
								<div>&nbsp;</div>
							</td>
						</tr>
						<tr>
							<td class="align-middle">
								비밀번호
								<div>&nbsp;</div>
							</td>
							<td>
								<input class="form-control" type="password" id="memberPw" name="memberPw">
								<div id="memberPwCheck">&nbsp;</div>
							</td>
						</tr>
					</table>
				</form>
				<div>
					<button id="signUpButton" class="btn btn-outline-primary text-center" type="button">회원가입</button>
				</div>
				<script>
					
					// 아이디 중복 여부를 확인할 변수 (입력한 아이디가 DB에 존재하지 않아야 통과(값이 0이 되야 통과))
					let existMemberId = 1;
					
					// ajax와 json 방식을 통해 페이지 이동 없이 CheckIdController의 로직을 수행
					// 중복 검사를 하기 전, 입력받은 아이디 값이 공백이라면, 먼저 아이디 값을 입력해 달라고 알림
					// json-simple-1.1.1.jar 파일을 사용(maven repository)
					// 참고 사이트: https://velog.io/@cocodori/ServletJSP%EC%97%90%EC%84%9C-Ajax-%EC%82%AC%EC%9A%A9
					$('#memberIdCheckButton').click(function() {
						if ($('#memberId').val() == '') {
							$('#memberIdCheck').html($('<small style="color:red;">').text("아이디를 입력해 주세요."));
						} else {
							$.ajax({
								url: '${pageContext.request.contextPath}/checkId',
								type: 'post',
								data: {'memberId': $('#memberId').val()},
								success:function(data, textStatus) {
									console.log('중복 검사 성공');
									console.log('data:' + data);
									const jsonCheckId = JSON.parse(data);
									
									if (jsonCheckId.checkId == true) {
										existMemberId = 0;
										$('#memberIdCheck').html($('<small style="color:green;">').text("사용 가능한 아이디입니다."));
									} else {
										$('#memberIdCheck').html($('<small style="color:red;">').text("이미 사용중인 아이디입니다."));
									}
								},
								error : function(data,textStatus) {
					                console.log('중복 검사 실패')
								}
							})
						}
								
					})
					
					/* 유효성 검사 이벤트 */
					// 입력받아야 할 항목들의 값이 공백인지 아닌지, 아이디 중복 검사는 통과하였는지를 검사하고
					// 부족한 것이 있다면 이를 알려 이용자에게 정상적으로 값을 받을 수 있게 끔 구현
 					$('#signUpButton').click(function() {
						
 						// 중복 검사
						if (existMemberId !=0 ) {
							
							$('#memberIdCheck').html($('<small style="color:red;">').text("중복검사를 통과해 주세요."));
						
						// 비밀번호 검사
						} else if ($('#memberPw').val() == '') {
							
							$('#memberPwCheck').html($('<small style="color:red;">').text("비밀번호를 입력해 주세요."));
						
						// 모든 유효성 검사를 통과하였으니 회원가입 승인
						} else {
							
							// 유효성 검사를 전부 통과하면 회원가입
							$('#signUpForm').submit();
						}
						
					})
				</script>
			</div>
		</div>
	</div>
</body>
</html>