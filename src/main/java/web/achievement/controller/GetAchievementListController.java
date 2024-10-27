package web.achievement.controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import web.achievement.dao.AchievementDao;
import web.achievement.dao.impl.AchievementDaoImpl;
import web.achievement.vo.Achievement;

@WebServlet("/achievement/getlist")
public class GetAchievementListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AchievementDao achievementDao;

	@Override
	public void init() throws ServletException {
		try {
			achievementDao = new AchievementDaoImpl();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
	    resp.setCharacterEncoding("UTF-8");

	    List<Achievement> achievements = achievementDao.selectAchievementsByUserId(2);

	    Gson gson = new Gson();

	    JsonObject response = new JsonObject();
	    JsonArray achievementsArray = new JsonArray();

	    for (Achievement achievement : achievements) {
	        JsonObject achievementJson = new JsonObject();
	        achievementJson.addProperty("aname", achievement.getAname());
	        achievementJson.addProperty("aTypeId", achievement.getaTypeId());
	        achievementJson.addProperty("content", achievement.getContent());

	        achievementJson.addProperty("finishtime",
	            achievement.getFinishtime() != null ? 
	            new SimpleDateFormat("yyyy-MM-dd").format(achievement.getFinishtime()) : null);

	        achievementJson.addProperty("photo", 
	            achievement.getPhoto() != null ? 
	            Base64.getEncoder().encodeToString(achievement.getPhoto()) : null);

	        achievementsArray.add(achievementJson);
	    }

	    response.add("achievements", achievementsArray);
	    
	    PrintWriter out = resp.getWriter();
	    out.println(gson.toJson(response));
	}
}
