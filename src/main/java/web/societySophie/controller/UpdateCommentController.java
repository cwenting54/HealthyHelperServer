package web.societySophie.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import web.societySophie.service.CommentService;
import web.societySophie.service.impl.CommentServiceImpl;
import web.societySophie.vo.Comment;


@WebServlet("/updateComment")
public class UpdateCommentController extends HttpServlet {
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
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		JsonObject reqBody = gson.fromJson(req.getReader(), JsonObject.class);
		Comment comment = new Comment();
		comment.setCommentId(reqBody.get("commentId").getAsInt());
		comment.setReply(reqBody.get("reply").getAsString());


		System.out.println("dataIn: " + comment.toString());

		String errMsg = commentService.updateComment(comment);

		JsonObject respBody = new JsonObject();
		respBody.addProperty("result", errMsg == null);
		respBody.addProperty("errMsg", errMsg);
		resp.setContentType(CONTENT_TYPE);
		resp.getWriter().write(respBody.toString());
		System.out.println("dataOut: " + respBody.toString());
	}
}
