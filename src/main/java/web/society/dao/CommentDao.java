package web.society.dao;

import java.util.List;

import web.society.vo.Comment;


public interface CommentDao {
	List<Comment> selectCommentByPostId(int postId);
	
	
	int insertComment(Comment comment, int postId, int userId);
}
