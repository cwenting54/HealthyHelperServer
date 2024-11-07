package web.map.vo;

public class HealthyMap {
    private Integer rID;
    private String rname;
    private String rweb;
    private String rcity;
    private String rregion;
    private String raddress;
    private String rphone;
    private Float rrating;
    private String rphotoUrl;
    private double rlongitude;
    private double rlatitude;
    private Integer ufid; 
    public HealthyMap() {
    }

    public HealthyMap(Integer rID, String rname, String rweb, String rcity, String rregion, 
                     String raddress, String rphone, Float rrating, String rphotoUrl, 
                     double rlongitude, double rlatitude, Integer ufid) { // 更新構造函數
        this.rID = rID;
        this.rname = rname;
        this.rweb = rweb;
        this.rcity = rcity;
        this.rregion = rregion;
        this.raddress = raddress;
        this.rphone = rphone;
        this.rrating = rrating;
        this.rphotoUrl = rphotoUrl;
        this.rlongitude = rlongitude;
        this.rlatitude = rlatitude;
        this.ufid = ufid; 
    }

    public Integer getRID() {
        return rID;
    }

    public void setRID(Integer rID) {
        this.rID = rID;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getRweb() {
        return rweb;
    }

    public void setRweb(String rweb) {
        this.rweb = rweb;
    }

    public String getRcity() {
        return rcity;
    }

    public void setRcity(String rcity) {
        this.rcity = rcity;
    }

    public String getRregion() {
        return rregion;
    }

    public void setRregion(String rregion) {
        this.rregion = rregion;
    }

    public String getRaddress() {
        return raddress;
    }

    public void setRaddress(String raddress) {
        this.raddress = raddress;
    }

    public String getRphone() {
        return rphone;
    }

    public void setRphone(String rphone) {
        this.rphone = rphone;
    }

    public Float getRrating() {
        return rrating;
    }

    public void setRrating(Float rrating) {
        this.rrating = rrating;
    }

    public String getRphotoUrl() {
        return rphotoUrl;
    }

    public void setRphotoUrl(String rphotoUrl) {
        this.rphotoUrl = rphotoUrl;
    }

    public double getRlongitude() {
        return rlongitude;
    }

    public void setRlongitude(double rlongitude) {
        this.rlongitude = rlongitude;
    }

    public double getRlatitude() {
        return rlatitude;
    }

    public void setRlatitude(double rlatitude) {
        this.rlatitude = rlatitude;
    }

    public Integer getUfid() { 
        return ufid;
    }

    public void setUfid(Integer ufid) { 
        this.ufid = ufid;
    }
}
