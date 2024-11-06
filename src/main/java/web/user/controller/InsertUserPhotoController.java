package web.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import web.user.service.UserPhotoService;
import web.user.service.impl.UserPhotoServiceImpl;


@WebServlet("/insertUserPhoto")
public class InsertUserPhotoController extends HttpServlet{
	private static final long serialVersionUID = 1L;
    private final static String CONTENT_TYPE = "application/json; charset=UTF-8";
    private UserPhotoService userPhotoService;
    
    @Override
	public void init() throws ServletException {
		try {
			userPhotoService = new UserPhotoServiceImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		JsonObject reqBody = gson.fromJson(req.getReader(), JsonObject.class);
		int userId = reqBody.get("userId").getAsInt();
		String photoUrl = reqBody.get("photoUrl").getAsString();
		System.out.println("dataIn: " + reqBody.toString());
		String errMsg = userPhotoService.inserUserPhotoService(userId, photoUrl);
		
		JsonObject respBody = new JsonObject();		
		respBody.addProperty("result", errMsg == null);
		respBody.addProperty("errMsg", errMsg);		
		resp.setContentType(CONTENT_TYPE);
		resp.getWriter().write(respBody.toString());
		System.out.println("dataOut: " + respBody.toString());

       
    }
}
