package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commons.DBUtil;
import dao.TodoDao;
import vo.Todo;

public class CalendarService {
	private TodoDao todoDao;
	
   public Map<String, Object> getTargetCalendar(String memberId, String currentYear, String currentMonth, String option) { // option : pre, next
	      // 1. �떖�젰 肄붾뱶
	      Map<String, Object> map = new HashMap<>();
	      
	      Calendar c = Calendar.getInstance(); // �삤�뒛 �궇吏쒖쓽 �뀈�룄�� �썡�쓣 媛�吏꾨떎
	      
	      if(currentYear != null && currentMonth != null) {
	         int y = 0;
	         int m = 0;
	         y = Integer.parseInt(currentYear);
	         // 1�썡 ~ 12�썡
	         m = Integer.parseInt(currentMonth);
	         if(option != null && option.equals("pre")) {
	            m = m-1; // issue : 0�씪�븣
	            if(m == 0) {
	               m = 12;      
	               y--; // y-=1; y = y-1;
	            }
	         } else if(option != null && option.equals("next")) {
	            m = m+1; // issue : 13�씪�븣
	            if(m == 13) {
	               m = 1;
	               y++;
	            }
	         }
	         c.set(Calendar.YEAR, y);
	         c.set(Calendar.MONTH, m-1);// 0�썡~11�썡
	      }
	   
	      c.set(Calendar.DATE, 1); // c媛앹껜 �삤�뒛�쓽 �젙蹂� -> 媛숈� �떖 1�씪濡� 蹂�寃�
	      // �떖�젰�뿉 �븘�슂�븳 �뜲�씠�꽣
	      int targetYear = c.get(Calendar.YEAR);
	      int targetMonth = c.get(Calendar.MONTH) + 1;
	      int endDay = c.getActualMaximum(Calendar.DATE);
	      // �떖�젰 �븵,�뮘 怨듬갚�쓽 媛쒖닔
	      int startBlank = 0; // ��耳볦씠 �릺�뒗 �떖�쓽 1�씪�쓽 �슂�씪 -> �씪�슂�씪�씠硫� 0, �썡�슂�씪 1.... �넗�슂�씪�씠硫� 6�씠 �븘�슂
	      startBlank = c.get(Calendar.DAY_OF_WEEK) - 1;
	      
	      int endBlank = 0; // �쟾泥댁쓽 <td>媛쒖닔 = startBlank+endDay+endBlnk <- �씠媛믪씠 7濡� �굹�늻�뼱 �뼥�뼱吏��룄濡�
	      endBlank = 7 - (startBlank+endDay)%7;
	      if(endBlank == 7) {
	         endBlank = 0;
	      }
	      
	      map.put("targetYear", targetYear);
	      map.put("targetMonth", targetMonth);
	      map.put("endDay", endDay);
	      map.put("startBlank", startBlank);
	      map.put("endBlank", endBlank);
	      
	      // �떖�젰�뿉 異붽��븷 紐⑤뜽 �븣怨좊━利�
	      List<Todo> list = null;
	      Connection conn = null;
	      try {
	         conn = DBUtil.getConnection("jdbc:mariadb://13.125.218.194:3306/todo", "root", "java1004");
	         todoDao = new TodoDao();
	         Todo todo = new Todo(); 
	         // memberId <- 留ㅺ컻蹂��닔濡� �엯�젰諛쏆옄
	         // todoDate�쓽 �뀈 �썡 <- targetYear��targetMonth瑜� �궗�슜�빐�꽌...
	         todo.setMemberId(memberId);
	         
	         String strYear = ""+targetYear;
	         String strMonth = ""+targetMonth;
	         if(targetMonth < 10) {
	            strMonth = "0"+targetMonth;
	         }
	         todo.setTodoDate(strYear+"-"+strMonth); 
	         // �뵒踰꾧퉭
	         System.out.println(todo+" <--todo");
	         
	         list = todoDao.selectTodoListByMonth(conn, todo);
	      } catch(Exception e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            conn.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      map.put("todoList", list);
	      
	      return map;
	   }

}
