package web.dietdiary.controller;

import java.io.IOException;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import web.dietdiary.checker.impl.DietDiaryChecker;
import web.dietdiary.checker.impl.DietDiaryCheckerImpl;
import web.dietdiary.service.impl.DietDiaryService;
import web.dietdiary.service.impl.DietDiaryServiceImpl;
import web.dietdiary.util.gson.GsonForSqlDateAndSqlTime;
import web.dietdiary.vo.DietDiaryVO;

@WebServlet("/dietDiary/insert/insertDietDiary")
public class InsertDietDiaryController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private DietDiaryService dietDiaryService;
	private DietDiaryChecker dietDiaryChecker;
	
	@Override
	public void init() throws ServletException {
		try {
			this.dietDiaryService = new DietDiaryServiceImpl();
			this.dietDiaryChecker = new DietDiaryCheckerImpl();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override 
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        System.out.println("--------------------------------------------");
        System.out.println("`doPost` method of class with annotation `@WebServlet(\"/dietDiary/insert/insertDietDiary\")` was called.");
        
		Gson gson = GsonForSqlDateAndSqlTime.gson;
		int affectedRows = -1;
		boolean isValidData = true;
		DietDiaryVO targetDietDiary = gson.fromJson(req.getReader(), DietDiaryVO.class);
		
		System.out.println();
		System.out.println();
		System.out.println("targetDietDiary:"+targetDietDiary);
		System.out.println();
		System.out.println();
		
		isValidData = this.dietDiaryChecker.check(targetDietDiary);
		affectedRows = this.dietDiaryService.insert(targetDietDiary);
		 
		if(!isValidData) {
	        System.out.println("`doPost` method of class with annotation `@WebServlet(\"/dietDiary/insert/insertDietDiary\")` was finished to called.");
			System.out.println("--------------------------------------------");
	        
			resp.getWriter().write(gson.toJson(-3));	
	        return;
		}
		
        System.out.println("`doPost` method of class with annotation `@WebServlet(\"/dietDiary/insert/insertDietDiary\")` was finished to called.");
		System.out.println("--------------------------------------------");
        
		resp.getWriter().write(gson.toJson(affectedRows));		
		return;
	}
}
