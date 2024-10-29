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
import web.dietdiary.vo.DietDiary;

@WebServlet("/dietDiary/insert/insertDietDiary")
public class InsertDietDiaryController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private DietDiaryService dietDiaryService;
	private DietDiaryChecker dietDiaryChecker;
	
	@Override
	public void init() throws ServletException {
		try {
			this.dietDiaryService = new DietDiaryServiceImpl(null);
			this.dietDiaryChecker = new DietDiaryCheckerImpl();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override 
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException{
		Gson gson = GsonForSqlDateAndSqlTime.gson;
		JsonObject jsonObject = new JsonObject();
		String errorMessage = "";
		boolean isValidData = true;
		DietDiary dietDiary = gson.fromJson(req.getReader(), DietDiary.class);
		System.out.println("Ready to deserialize.");
		System.out.println("dietDiary:"+dietDiary.toString());
		
		isValidData = this.dietDiaryChecker.check(dietDiary);
		if(!isValidData) {
			errorMessage = "Invalid Data in DietDiary!!!";
			jsonObject.addProperty("result", false);
			jsonObject.addProperty("errorMessage", errorMessage);
			res.getWriter().write(jsonObject.toString());	
			return;
		}
		errorMessage = this.dietDiaryService.insert(dietDiary);
		 
		if(errorMessage!="") {
			jsonObject.addProperty("result", false);
			jsonObject.addProperty("errorMessage", errorMessage);
			res.getWriter().write(jsonObject.toString());		
			return;
		}
		
		jsonObject.addProperty("result", true);
		jsonObject.addProperty("errorMessage", errorMessage);
		res.getWriter().write(jsonObject.toString());
		return;
	}
}