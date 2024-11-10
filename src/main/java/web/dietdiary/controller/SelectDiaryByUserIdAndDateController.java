package web.dietdiary.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web.dietdiary.service.impl.DietDiaryService;
import web.dietdiary.service.impl.DietDiaryServiceImpl;
import web.dietdiary.util.gson.GsonForSqlDateAndSqlTime;
import web.dietdiary.vo.DietDiaryVO;

@WebServlet("/dietDiary/diary/selectByUserIdAndDate")
public class SelectDiaryByUserIdAndDateController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private DietDiaryService dietDiaryService;
	
	@Override
	public void init() throws ServletException {
		try {
			this.dietDiaryService = new DietDiaryServiceImpl();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override 
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
		Gson gson = GsonForSqlDateAndSqlTime.gson;
		DietDiaryVO targetDietDiary = gson.fromJson(req.getReader(), DietDiaryVO.class);
		ArrayList<DietDiaryVO> queriedDietDiaries = this.dietDiaryService.selectByUserIdAndDate(targetDietDiary);
		resp.getWriter().write(gson.toJson(queriedDietDiaries));
		return;
	}
}