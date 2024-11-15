package web.dietdiary.controller;

import java.io.IOException;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import web.dietdiary.constant.SqlDatePattern;
import web.dietdiary.service.impl.DietDiaryService;
import web.dietdiary.service.impl.DietDiaryServiceImpl;
import web.dietdiary.util.datetime.DateTimeHandler;
import web.dietdiary.util.datetime.DateTimeHandlerImpl;
import web.dietdiary.util.gson.GsonForSqlDateAndSqlTime;
import web.dietdiary.vo.DietDiaryVO;

@WebServlet("/dietDiary/query/byTime")
public class QueryDietDiaryByTimeController extends HttpServlet {
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
		String errorMessage = "";
		DietDiaryVO dietDiary = gson.fromJson(req.getReader(), DietDiaryVO.class);
		ArrayList<DietDiaryVO> dietDiaries = this.dietDiaryService.search(dietDiary,3);		
		System.out.println("dietDiary:"+dietDiary);	 
		if(dietDiaries == null) {
			JsonObject jsonObject = new JsonObject();
			errorMessage = "Unknown error!!!";
			jsonObject.addProperty("errorMessage", errorMessage);
			resp.getWriter().write(jsonObject.toString());
			return;
		}
		resp.getWriter().write(gson.toJson(dietDiaries));	
		return;
	}
}
