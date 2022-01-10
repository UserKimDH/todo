package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import commons.DBUtil;
import dao.TodoDao;
import service.CalendarService;
import vo.Member;
import vo.Todo;

/**
 * Servlet implementation class CalendarController
 */
@WebServlet("/member/calendar")
public class CalendarController extends HttpServlet {
	private CalendarService calendarService;
	private TodoDao todoDao;
	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      
	      String currentYear = request.getParameter("currentYear");
	      String currentMonth = request.getParameter("currentMonth");
	      String option = request.getParameter("option");
	      
	      // 디버깅
	      
	      calendarService = new CalendarService();
	      String memberId = ((Member) request.getSession().getAttribute("loginMember")).getMemberId();
	      //디버깅
	      Map<String, Object> map = calendarService.getTargetCalendar(memberId, currentYear, currentMonth, option);
	      
	      // 모델
	      request.setAttribute("targetYear", map.get("targetYear"));
	      request.setAttribute("targetMonth", map.get("targetMonth"));
	      request.setAttribute("endDay", map.get("endDay"));
	      // 달력을 출력할대 앞/뒤 필요한 공백<td>
	      request.setAttribute("startBlank", map.get("startBlank"));
	      request.setAttribute("endBlank", map.get("endBlank"));
	      // 달력에 출력할 todo 모델 목록
	      request.setAttribute("todoList", map.get("todoList"));
	      
	      request.getRequestDispatcher("/WEB-INF/view/calendar.jsp").forward(request, response);
	   }


}
