package web.map.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import web.map.service.HealthyMapService;
import web.map.service.impl.HealthyMapServiceImpl;
import web.map.vo.HealthyMap;

@WebServlet("/deleteFavorRestaurant")
public class DeleteUserFavorRestaurantController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "application/json; charset=UTF-8";
	private HealthyMapService healthyMapService;

	@Override
	public void init() throws ServletException {
		try {
			healthyMapService = new HealthyMapServiceImpl();
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
		int rid = reqBody.get("rid").getAsInt();
		
	    String errMsg = healthyMapService.deleteUserFavorRestaurant(userId, rid);

	    JsonObject respBody = new JsonObject();		
		respBody.addProperty("result", errMsg == null);
		respBody.addProperty("errMsg", errMsg);		
		resp.setContentType(CONTENT_TYPE);
		resp.getWriter().write(respBody.toString());
		System.out.println("dataOut: " + respBody.toString());
	}

}
