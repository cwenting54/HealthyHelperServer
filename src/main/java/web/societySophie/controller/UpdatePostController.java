package web.societySophie.controller;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import web.societySophie.service.PostService;
import web.societySophie.service.impl.PostServiceImpl;
import web.societySophie.vo.Post;

@WebServlet("/updatePost")
public class UpdatePostController extends HttpServlet {
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
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		JsonObject reqBody = gson.fromJson(req.getReader(), JsonObject.class);
		Post post = new Post();
		post.setPostId(reqBody.get("postId").getAsInt());
		post.setTitle(reqBody.get("title").getAsString());
		post.setContent(reqBody.get("content").getAsString());
		JsonElement pictureElement = reqBody.get("picture");
		if (pictureElement != null && !pictureElement.isJsonNull()) {
	        String base64Image = pictureElement.getAsString();
	        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
	        post.setPicture(imageBytes);
	    } else {
	    	 post.setPicture(null);
	    }

		System.out.println("dataIn: " + post.toString());

		String errMsg = postService.updatePost(post);

		JsonObject respBody = new JsonObject();
		respBody.addProperty("result", errMsg == null);
		respBody.addProperty("errMsg", errMsg);
		resp.setContentType(CONTENT_TYPE);
		resp.getWriter().write(respBody.toString());
		System.out.println("dataOut: " + respBody.toString());
	}
}
