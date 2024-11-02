package web.society.vo;

import java.sql.Timestamp;

public class Comment {
    private Integer commentId;
    private Integer userId;
    private Integer postId;
    private String reply;
    private Timestamp commDate;
    private Integer likeComm;

    public Comment() {
    }

    public Comment(Integer commentId, Integer userId, Integer postId, String reply, Timestamp commDate, Integer likeComm) {
        this.commentId = commentId;
        this.userId = userId;
        this.postId = postId;
        this.reply = reply;
        this.commDate = commDate;
        this.likeComm = likeComm;
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
}

