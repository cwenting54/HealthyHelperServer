package web.societySophie.controller;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import web.societySophie.service.PostService;
import web.societySophie.service.impl.PostServiceImpl;
import web.societySophie.vo.Post;

@WebServlet("/insertPost")
public class InsertPostByUserIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "application/json; charset=UTF-8";
	private PostService postService;

	@Override
	public void init() throws ServletException {
		try {
			postService = new PostServiceImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType(CONTENT_TYPE);
		resp.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		JsonObject reqBody = gson.fromJson(req.getReader(), JsonObject.class);
		int userId = reqBody.get("userId").getAsInt();
		Post post = new Post();
		post.setTitle(reqBody.get("title").getAsString());
		post.setContent(reqBody.get("content").getAsString());
		String pictureBase64 = reqBody.get("picture").getAsString();
		if (pictureBase64 != null && !pictureBase64.isEmpty()) {
			byte[] pictureBytes = Base64.getDecoder().decode(pictureBase64);
			post.setPicture(pictureBytes);
		} else {
			post.setPicture(null);
		}

		String errMsg = postService.insertPost(post, userId);
		
		JsonObject respBody = new JsonObject();
		respBody.addProperty("result", errMsg == null);
		respBody.addProperty("errMsg", errMsg);
		resp.setContentType(CONTENT_TYPE);
		resp.getWriter().write(respBody.toString());
		System.out.println("dataOut: " + respBody.toString());

	}
}
