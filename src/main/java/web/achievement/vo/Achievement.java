package web.achievement.vo;

import java.sql.Timestamp;

public class Achievement {
    private Integer aId;
    private Integer aTypeId;
    private String aname;
    private String content;
    private byte[] photo;
    private Timestamp finishtime;
    

    public Achievement() {
    }

    public Achievement(Integer aId, Integer aTypeId, String aname, String content, byte[] photo, Timestamp finishtime) {
        this.aId = aId;
        this.aTypeId = aTypeId;
        this.aname = aname;
        this.content = content;
        this.photo = photo;
        this.finishtime = finishtime;
    }

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public Integer getaTypeId() {
        return aTypeId;
    }

    public void setaTypeId(Integer aTypeId) {
        this.aTypeId = aTypeId;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }   
    public Timestamp getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(Timestamp finishtime) {
        this.finishtime = finishtime;
    }

}

