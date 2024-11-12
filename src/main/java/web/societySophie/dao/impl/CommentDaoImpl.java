package web.societySophie.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.societySophie.dao.CommentDao;
import web.societySophie.vo.Comment;

public class CommentDaoImpl implements CommentDao {
	private DataSource ds;

	public CommentDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/iHealth");
	}

	@Override
	public List<Comment> selectComment() {
		String sql = "select c.commentId, c.userId, c.postId, c.reply, c.commdate, c.likecomm, u.username, u.photoUrl "
				+ "from comment c join user u " + "on c.userId = u.userId ";
		try (
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
		) {

			var list = new ArrayList<Comment>();
			while (rs.next()) {
				Comment comment = new Comment();
				comment.setCommentId(rs.getInt("commentId"));
				comment.setUserId(rs.getInt("userId"));
				comment.setPostId(rs.getInt("postId"));
				comment.setReply(rs.getString("reply"));
				comment.setCommDate(rs.getTimestamp("commdate"));
				comment.setLikeComm(rs.getInt("likecomm"));
				comment.setUserName(rs.getString("username"));
				comment.setPhotoUrl(rs.getString("photoUrl"));
				list.add(comment);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertComment(Comment comment, int postId, int userId) {
		String sql = "insert into comment(userid, postId, reply) values(?, ?, ?)";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, userId);
			pstmt.setInt(2, postId);
			pstmt.setString(3, comment.getReply());

			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int updateComment(Comment comment) {
		String sql = "update comment set reply = ? where commentId = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, comment.getReply());
			pstmt.setInt(2, comment.getCommentId());

			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int deleteComment(int commentId) {
		String sql = "delete from comment where commentId = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, commentId);

			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public Boolean isCommentLikeByUserId(int userId, int commentId) {
		String sql = "select count(*) from `like` where userId = ? and commentId = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, userId);
			pstmt.setInt(2, commentId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int likeComment(int userId, int commentId) {
		String sqlInsert = "insert into `like` (userId, commentId) values (?, ?)";
		String sqlUpdate = "update comment set likecomm = likecomm + 1 where commentId = ?";

		try (Connection conn = ds.getConnection()) {
			conn.setAutoCommit(false);

			try (PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert);
					PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdate)) {

				pstmtInsert.setInt(1, userId);
				pstmtInsert.setInt(2, commentId);
				int insertResult = pstmtInsert.executeUpdate();

				if (insertResult > 0) {
					pstmtUpdate.setInt(1, commentId);
					pstmtUpdate.executeUpdate();
					conn.commit();
					return 1;
				} else {
					conn.rollback();
					return 0;
				}
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int deleteCommentLike(int userId, int commentId) {
		String sqlDelete = "delete from `like` where userId = ? and commentId = ?";
		String sqlUpdate = "update comment set likecomm = likecomm - 1 where commentId = ?";

		try (Connection conn = ds.getConnection()) {
			conn.setAutoCommit(false);

			try (PreparedStatement pstmtDelete = conn.prepareStatement(sqlDelete);
					PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdate)) {

				pstmtDelete.setInt(1, userId);
				pstmtDelete.setInt(2, commentId);
				int deleteResult = pstmtDelete.executeUpdate();

				if (deleteResult > 0) {
					pstmtUpdate.setInt(1, commentId);
					pstmtUpdate.executeUpdate();
					conn.commit();
					return 1;
				} else {
					conn.rollback();
					return 0;
				}
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
