package web.societySophie.service.impl;

import java.util.List;
import java.util.Optional;

import javax.naming.NamingException;
import web.societySophie.dao.CommentDao;
import web.societySophie.dao.impl.CommentDaoImpl;
import web.societySophie.service.CommentService;
import web.societySophie.vo.Comment;

public class CommentServiceImpl implements CommentService{
	private CommentDao commentDao;

	public CommentServiceImpl() throws NamingException {
		commentDao = new CommentDaoImpl();
	}

	@Override
	public List<Comment> selectComment() {
		return commentDao.selectComment();
	}

	@Override
	public String insertComment(Comment comment, int userId, int postId) {
		if(userId <= 0 || postId <= 0) {
			return "尚未登入或未指定貼文進行留言";
		}
		
		String reply = comment.getReply();
		if (reply == null ) {
			return "回覆內容須填寫";
		} 
		
		
		int result = commentDao.insertComment(comment, postId, userId);

		return result > 0 ? null : "新增留言失敗";
	}

	@Override
	public String updateComment(Comment comment) {		
		String reply = comment.getReply();
		if (reply == null ) {
			return "回覆內容須填寫";
		} 		
		
		int result = commentDao.updateComment(comment);

		return result > 0 ? null : "新增留言失敗";
	}

	@Override
	public String deleteComment(int commentId) {
		if (commentId <= 0) {
			return "無法刪除";
		}
		int result = commentDao.deleteComment(commentId);
		return result > 0 ? null : "刪除留言失敗";
	}

	@Override
	public String likeComment(int userId, int commentId) {
		if(commentDao.isCommentLikeByUserId(userId, commentId) == true) {
			return "重複新增";
		}
		int result = commentDao.likeComment(userId, commentId);
		return result > 0 ? null : "新增貼文讚數失敗";
	}

	@Override
	public String deleteLikeComment(int userId, int commentId) {
		if (commentId <= 0) {
			return "無法刪除";
		}

		int result = commentDao.deleteCommentLike(userId, commentId);
		return result > 0 ? null : "刪除留言讚數失敗";
	}

}
