package web.societySophie.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

		List<Comment> comments = commentService.selectComment();
		JsonArray commentsArray = new JsonArray();

		for (Comment comment : comments) {
			JsonObject commentJson = new JsonObject();
			commentJson.addProperty("commentId", comment.getCommentId());
			commentJson.addProperty("userId", comment.getUserId());
			commentJson.addProperty("postId", comment.getPostId());
			commentJson.addProperty("reply", comment.getReply());
			commentJson.addProperty("commdate",
					comment.getCommDate() != null
							? new SimpleDateFormat("yyyy-MM-dd HH:mm").format(comment.getCommDate())
							: null);
			commentJson.addProperty("likecomm", comment.getLikeComm());
			commentJson.addProperty("userName", comment.getUserName());
			commentJson.addProperty("photoUrl", comment.getPhotoUrl());

			commentsArray.add(commentJson);
		}


	    JsonObject respBody = new JsonObject();
	    respBody.add("data", commentsArray);
	    respBody.addProperty("result", !commentsArray.isEmpty());
	    respBody.addProperty("errMsg", commentsArray.isEmpty() ? "無資料" : null);

	    resp.setContentType(CONTENT_TYPE);
	    resp.getWriter().write(respBody.toString());
	    System.out.println("dataOut: " + respBody.toString());

	}
}
