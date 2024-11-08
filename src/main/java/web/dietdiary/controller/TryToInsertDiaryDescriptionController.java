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
import com.google.gson.JsonObject;

import web.dietdiary.service.impl.DiaryDescriptionService;
import web.dietdiary.service.impl.DiaryDescriptionServiceImpl;
import web.dietdiary.vo.DiaryDescriptionVO;

@WebServlet("/dietDiary/diaryDescription/tryToInsert")
public class TryToInsertDiaryDescriptionController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private DiaryDescriptionService diaryDescriptionService;
	
	@Override
	public void init() throws ServletException {
		try {
			this.diaryDescriptionService = new DiaryDescriptionServiceImpl();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        String errorMessage = "";
        int affectedRows = 1;
        DiaryDescriptionVO targetDiaryDescriptionVO = gson.fromJson(req.getReader(),DiaryDescriptionVO.class);
        affectedRows = this.diaryDescriptionService.tryToInsert(targetDiaryDescriptionVO);
        if(affectedRows!=1) {
        	errorMessage = "Unknown Error!!!";
        	jsonObject.addProperty("errorMessage", errorMessage);
        	jsonObject.addProperty("result", false);
        	resp.getWriter().write(jsonObject.toString());
        	return;
        }
        
        errorMessage = "";
    	jsonObject.addProperty("errorMessage", errorMessage);
    	jsonObject.addProperty("result", true);
    	resp.getWriter().write(jsonObject.toString());
    	return;
	}
}
