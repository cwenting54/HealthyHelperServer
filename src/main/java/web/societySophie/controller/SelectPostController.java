package web.societySophie.controller;

import java.io.IOException;
import java.io.PrintWriter;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import web.societySophie.service.PostService;
import web.societySophie.service.impl.PostServiceImpl;
import web.societySophie.vo.Post;

@WebServlet("/selectPost")
public class SelectPostController extends HttpServlet {
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

		List<Post> posts = postService.selectPost();
		JsonArray postsArray = new JsonArray();

		for (Post post : posts) {
			JsonObject postJson = new JsonObject();
			postJson.addProperty("postId", post.getPostId());
			postJson.addProperty("userId", post.getUserId());
			postJson.addProperty("title", post.getTitle());
			postJson.addProperty("content", post.getContent());
			postJson.addProperty("picture",
					post.getPicture() != null ? Base64.getEncoder().encodeToString(post.getPicture()) : null);
			postJson.addProperty("postDate",
					post.getPostDate() != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(post.getPostDate())
							: null);
			postJson.addProperty("likepost", post.getLikePost());
			postJson.addProperty("userName", post.getUserName());
			postJson.addProperty("photoUrl", post.getPhotoUrl());

			postsArray.add(postJson);
		}

		try (PrintWriter out = resp.getWriter()) {
			out.println(postsArray.toString());
			System.out.println("dataOut: " + postsArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
