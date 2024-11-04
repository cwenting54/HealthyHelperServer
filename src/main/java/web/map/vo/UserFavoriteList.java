package web.map.vo;

public class UserFavoriteList {
    private Integer ufid;   
    private Integer userid;  
    private Integer rid;    


    public UserFavoriteList() {}


    public UserFavoriteList(Integer ufid, Integer userid, Integer rid) {
        this.ufid = ufid;
        this.userid = userid;
        this.rid = rid;
    }


    public Integer getUfid() {
        return ufid;
    }

    public void setUfid(Integer ufid) {
        this.ufid = ufid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
}
