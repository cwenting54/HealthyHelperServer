package web.user.controller;

import java.io.IOException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import web.user.dao.UserPhotoDao;
import web.user.dao.impl.UserPhotoDaoImpl;


@WebServlet("/selectUserPhoto")
public class SelectUserPhotoController extends HttpServlet{
	private static final long serialVersionUID = 1L;
    private final static String CONTENT_TYPE = "application/json; charset=UTF-8";
    private UserPhotoDao userPhotoDao;
    
    public SelectUserPhotoController() throws NamingException {
        this.userPhotoDao = new UserPhotoDaoImpl();
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		JsonObject reqBody = gson.fromJson(req.getReader(), JsonObject.class);
		int userId = reqBody.get("userId").getAsInt();
		String photoUrl = userPhotoDao.selectUserPhoto(userId);
		
		JsonObject respBody = new JsonObject();			
		respBody.addProperty("photoUrl", photoUrl);	
		resp.setContentType(CONTENT_TYPE);
		resp.getWriter().write(respBody.toString());
		System.out.println("dataOut: " + respBody.toString());

       
    }
}
