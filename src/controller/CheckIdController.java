package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import service.MemberService;

/**
 * Servlet implementation class CheckIdController
 */
@WebServlet("/checkId")
public class CheckIdController extends HttpServlet {
	private MemberService memberService;
	
	// 아이디 중복 여부를 체크
	// json-simple 라이브러리를 사용하여 ajax를 json으로 주고받을 수 있도록 구현
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		
		// 데이터 전송을 위한 JSON 오브젝트
		JSONObject jobj = new JSONObject();
		
		// 아이디 중복 검사
		memberService = new MemberService();
		boolean confirm = memberService.checkMemberId(memberId);
		
		if (confirm==false) {
			System.out.println("[debug] AddMemberController => 중복 검사 통과 실패");
		} else {
			System.out.println("[debug] AddMemberController => 중복 검사 통과 성공");
		}
			
		// 중복 검사 여부를 JSON 오브젝트에 저장
		jobj.put("checkId", confirm);
		
		// json 오브젝트를 문자열로 변환
		String jsonCheckId = jobj.toJSONString();
		
		// 전송
		request.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonCheckId);
		
	}

}
