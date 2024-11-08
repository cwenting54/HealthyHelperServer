package web.societySophie.vo;

import java.sql.Timestamp;

public class Comment {
    private Integer commentId;
    private Integer userId;
    private Integer postId;
    private String reply;
    private Timestamp commDate;
    private Integer likeComm;
    private String userName;
    private String photoUrl;   

	public Comment() {
    }

    public Comment(Integer commentId, Integer userId, Integer postId, String reply, Timestamp commDate, Integer likeComm, String userName, String photoUrl) {
        this.commentId = commentId;
        this.userId = userId;
        this.postId = postId;
        this.reply = reply;
        this.commDate = commDate;
        this.likeComm = likeComm;
        this.userName = userName;
        this.photoUrl = photoUrl;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Timestamp getCommDate() {
        return commDate;
    }

    public void setCommDate(Timestamp commDate) {
        this.commDate = commDate;
    }

    public Integer getLikeComm() {
        return likeComm;
    }

    public void setLikeComm(Integer likeComm) {
        this.likeComm = likeComm;
    }
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
}

