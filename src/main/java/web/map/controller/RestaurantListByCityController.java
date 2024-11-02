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

@WebServlet("/selectRestaurantByCity")
public class RestaurantListByCityController extends HttpServlet {
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
		String rcity = reqBody.get("rcity").getAsString();
		
	    List<HealthyMap> restaurants = healthyMapService.selectRestaurantByCity(rcity);

	    JsonArray dataArray = gson.toJsonTree(restaurants).getAsJsonArray();

	    JsonObject respBody = new JsonObject();
	    respBody.add("data", dataArray);
	    respBody.addProperty("result", !dataArray.isEmpty());
	    respBody.addProperty("errMsg", dataArray.isEmpty() ? "無資料" : null);

	    resp.setContentType(CONTENT_TYPE);
	    resp.getWriter().write(respBody.toString());
	    System.out.println("dataOut: " + respBody.toString());
	}

}
