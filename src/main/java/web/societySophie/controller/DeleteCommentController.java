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


@WebServlet("/deleteComment")
public class DeleteCommentController extends HttpServlet {
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
		int commentId = reqBody.get("commentId").getAsInt();


		String errMsg = commentService.deleteComment(commentId);
		JsonObject respBody = new JsonObject();
		respBody.addProperty("result", errMsg == null);
		respBody.addProperty("errMsg", errMsg);
		resp.setContentType(CONTENT_TYPE);
		resp.getWriter().write(respBody.toString());
		System.out.println("dataOut: " + respBody.toString());
	}
}
