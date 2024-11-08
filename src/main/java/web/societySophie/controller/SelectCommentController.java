package web.societySophie.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import web.societySophie.service.CommentService;
import web.societySophie.service.impl.CommentServiceImpl;
import web.societySophie.vo.Comment;


@WebServlet("/selectComment")
public class SelectCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "application/json; charset=UTF-8";
	private CommentService commentService;

	@Override
	public void init() throws ServletException {
		try {
			commentService = new CommentServiceImpl();
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
		int postId = reqBody.get("postId").getAsInt();

		List<Comment> comments = commentService.selectComment(postId);
		JsonArray commentsArray = new JsonArray();

		for (Comment comment : comments) {
			JsonObject commentJson = new JsonObject();
			commentJson.addProperty("commentId", comment.getCommentId());
			commentJson.addProperty("userId", comment.getUserId());
			commentJson.addProperty("postId", comment.getPostId());
			commentJson.addProperty("reply", comment.getReply());
			commentJson.addProperty("commdate",
					comment.getCommDate() != null
							? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(comment.getCommDate())
							: null);
			commentJson.addProperty("likecomm", comment.getLikeComm());

			commentsArray.add(commentJson);
		}

		resp.getWriter().println(commentsArray.toString());
		System.out.println("dataOut: " + commentsArray.toString());

	}
}