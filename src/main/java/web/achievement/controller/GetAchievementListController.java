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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import web.achievement.dao.AchievementDao;
import web.achievement.dao.impl.AchievementDaoImpl;
import web.achievement.vo.Achievement;



@WebServlet("/achievement/getlist")
public class GetAchievementListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final static String CONTENT_TYPE = "application/json; charset=UTF-8";
    private AchievementDao achievementDao;

    public GetAchievementListController() throws NamingException {
        this.achievementDao = new AchievementDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(CONTENT_TYPE);
        resp.setCharacterEncoding("UTF-8");

        List<Achievement> achievements = achievementDao.selectAchievementsByUserId(2);
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

        try (PrintWriter out = resp.getWriter()) {
            out.println(achievementsArray.toString());
            System.out.println("dataOut: " + achievementsArray.toString());
        }catch (Exception e) {
			e.printStackTrace();
		}
    }
	

}
