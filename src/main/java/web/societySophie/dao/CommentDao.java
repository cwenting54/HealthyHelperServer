package web.societySophie.dao;

import java.util.List;

import web.societySophie.vo.Comment;


public interface CommentDao {
	List<Comment> selectComment();
	
	int insertComment(Comment comment, int postId, int userId);
	
	int updateComment(Comment comment);
	
	int deleteComment(int commentId);

	Boolean isCommentLikeByUserId(int userId, int commentId);

	int likeComment(int userId, int commentId);
	
	int deleteCommentLike(int userId, int commentId);
}
