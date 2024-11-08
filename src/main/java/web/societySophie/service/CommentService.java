package web.societySophie.service;

import java.util.List;
import java.util.Optional;

import web.societySophie.vo.Comment;

public interface CommentService {
	List<Comment> selectComment();

	String insertComment(Comment comment, int userId, int postId);

	String updateComment(Comment comment);
	
	String deleteComment(int commentId);

	String likeComment(int userId, int commentId);

	String deleteLikeComment(int userId, int commentId);
}
