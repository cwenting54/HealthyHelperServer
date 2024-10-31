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

import web.user.service.UserService;
import web.user.service.impl.UserServiceImpl;
import web.user.vo.User;

@WebServlet("/user/update")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService service;
	
	@Override
	public void init() throws ServletException {
		try {
			service = new UserServiceImpl();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		User user = gson.fromJson(req.getReader(), User.class);
		String errMsg = service.userUpdate(user);
	
		JsonObject respBody = new JsonObject();
		if(errMsg == null) {
			respBody.addProperty("username", user.getUsername());
			respBody.addProperty("gender", user.getGender());
			respBody.addProperty("phone", user.getPhoneno());
			respBody.addProperty("email", user.getUserEmail());
			respBody.addProperty("birthday", user.getBirthday().toString());
		}else {
			respBody.addProperty("errMsg", errMsg);
		}	
		
		resp.getWriter().write(respBody.toString());
	}
}
  