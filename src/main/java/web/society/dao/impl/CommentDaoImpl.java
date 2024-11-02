package web.society.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.society.dao.CommentDao;
import web.society.vo.Comment;
import web.society.vo.Post;

public class CommentDaoImpl implements CommentDao{
	private DataSource ds;

	public CommentDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/iHealth");
	}

	@Override
	public List<Comment> selectCommentByPostId(int postId) {
		String sql = "select * from comment where postId = ?";
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			try(ResultSet rs = pstmt.executeQuery();) {
				pstmt.setInt(1, postId);
				var list = new ArrayList<Comment>();
				while (rs.next()) {
					Comment comment= new Comment();
					comment.setUserId(rs.getInt("userId"));
					comment.setPostId(rs.getInt("postId"));
					comment.setReply(rs.getString("reply"));
					comment.setCommDate(rs.getTimestamp("commdate"));
					comment.setLikeComm(rs.getInt("likecomm"));
					list.add(comment);
				}
				return list;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertComment(Comment comment, int postId, int userId) {
		String sql = "insert into comment(userid, postId, reply, commdate, likecomm) values(?, ?, ?, ?, ?)";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {		
			pstmt.setInt(1, userId);
			pstmt.setInt(2, postId);
			pstmt.setString(3, comment.getReply());
			pstmt.setTimestamp(4, comment.getCommDate());
			pstmt.setInt(5, comment.getLikeComm());

			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

}
