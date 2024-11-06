package web.achievement.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import web.achievement.service.AchievementService;
import web.achievement.service.impl.AchievementServiceImpl;


@WebServlet("/insertAchievement")
public class InsertAchievementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "application/json; charset=UTF-8";
	private AchievementService achievementService;

	public InsertAchievementController() throws NamingException {
		this.achievementService = new AchievementServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		JsonObject reqBody = gson.fromJson(req.getReader(), JsonObject.class);
		int userId = reqBody.get("userId").getAsInt();
		Map<String, String> resultMessages = new HashMap<>();
		String resultDiery = achievementService.addDieryAchievement(userId);
		String resultWeight = achievementService.addWeightAchievement(userId);
		String resultPlan = achievementService.addPlanAchievement(userId);
		
		resultMessages.put("diaryAchievement", resultDiery);
		resultMessages.put("weightAchievement", resultWeight);
		resultMessages.put("planAchievement", resultPlan);

		JsonObject respBody = new JsonObject();
		respBody.add("result", gson.toJsonTree(resultMessages));
		resp.setContentType(CONTENT_TYPE);
		resp.getWriter().write(respBody.toString());
		System.out.println("dataOut: " + respBody.toString());
	}

}
