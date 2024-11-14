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

import web.dietdiary.service.impl.DiaryDescriptionService;
import web.dietdiary.service.impl.DiaryDescriptionServiceImpl;
import web.dietdiary.vo.DiaryDescriptionVO;

@WebServlet("/dietDiary/diaryDescription/select/selectDiaryDescriptionByDiaryId")
public class QueryDiaryDescriptionController extends HttpServlet {
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
        System.out.println("In QueryDiaryDescriptionController class,req.getReader():"+req.getReader().toString());
        DiaryDescriptionVO targetDiaryDescriptionVO = gson.fromJson(req.getReader(), DiaryDescriptionVO.class);
        ArrayList<DiaryDescriptionVO> diaryDescriptionVOs = new ArrayList<DiaryDescriptionVO>(); 
        diaryDescriptionVOs = this.diaryDescriptionService.selectById(targetDiaryDescriptionVO);
        
        resp.getWriter().write(gson.toJson(diaryDescriptionVOs));
        return;
	}
}