package web.society.controller;

import java.io.BufferedReader;
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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import web.society.service.PostService;
import web.society.service.impl.PostServiceImpl;
import web.society.vo.Post;

@WebServlet("/post")
public class GetPostListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "application/json; charset=UTF-8";
	private PostService postService;
@Override
    public void init() throws ServletException {
	try {
		postService = new PostServiceImpl();
	} catch (NamingException e) {
		e.printStackTrace();
	}
    }
//@Override
//protected void doPost(HttpServletRequest request, HttpServletResponse response)
//        throws ServletException, IOException {
//    response.setContentType("application/json; charset=UTF-8");
//
//    // 使用 Service 層取得所有貼文
//    List<Post> posts = postService.selectPost();
//
//    // 使用 Gson 將資料轉換成 JSON 格式
//    Gson gson = new Gson();
//    String json = gson.toJson(posts);
//
//    // 將 JSON 回傳到回應中
//    response.getWriter().write(json);
//}

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
