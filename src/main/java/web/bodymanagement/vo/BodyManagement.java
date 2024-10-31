package web.bodymanagement.vo;

import java.sql.Timestamp;

public class BodyManagement {
    private Integer recordId;
    private Integer userId;
    private Float height; 
    private Float weight;  
    private Float bodyFat; 
    private Timestamp recordDate;
    private Float bmi;    

    public BodyManagement() {}

    public BodyManagement(Integer recordId, Integer userId, Float height, Float weight, Float bodyFat, Timestamp recordDate, Float bmi) {
        this.recordId = recordId;
        this.userId = userId;
        this.height = height;
        this.weight = weight;
        this.bodyFat = bodyFat;
        this.recordDate = recordDate;
        this.bmi = bmi;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getBodyFat() { 
        return bodyFat;
    }

    public void setBodyFat(Float bodyFat) {
        this.bodyFat = bodyFat;
    }

    public Timestamp getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Timestamp recordDate) {
        this.recordDate = recordDate;
    }

    public Float getBmi() {
        return bmi;
    }

    public void setBmi(Float bmi) { 
        this.bmi = bmi;
    }
}
