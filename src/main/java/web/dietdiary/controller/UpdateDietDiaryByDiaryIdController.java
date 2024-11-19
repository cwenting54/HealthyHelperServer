package web.dietdiary.controller;

import java.io.IOException;

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

@WebServlet("/dietDiary/diary/update/updateDietDiary")
public class UpdateDietDiaryController extends HttpServlet{
	
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
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        System.out.println("-----------------------------------------");
        System.out.println("`doPost` method in class with annotation `@WebServlet(\"/dietDiary/update/updateDietDiary\")` was called.");
        
		Gson gson = GsonForSqlDateAndSqlTime.gson;
		DietDiaryVO targetDietDiary = gson.fromJson(req.getReader(), DietDiaryVO.class);
		int affectedRows = -1;
		
		System.out.println();
		System.out.println();
		System.out.println("targetDietDiary:"+targetDietDiary);
		System.out.println();
		System.out.println();
		
		affectedRows = this.dietDiaryService.updateDietDiary(targetDietDiary);
	
		System.out.println();
		System.out.println();
		System.out.println("affectedRows:"+affectedRows);
		System.out.println();
		System.out.println();
		
		resp.getWriter().write(gson.toJson(affectedRows));
		
        System.out.println("`doPost` method in class with annotation `@WebServlet(\"/dietDiary/update/updateDietDiary\")` was finished to called.");
		System.out.println("-----------------------------------------");
		
		return;
	}
}
