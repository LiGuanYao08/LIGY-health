package com.LIGY.pojo;

import java.io.Serializable;

/**
 * 疾病库
 */
public class Ill implements Serializable {
    private Integer id;//id
    private String code;//编号
    private String nameOfDisease;//疾病名称
    private String type;//异常类型
    private String occupational;//是否职业病
    private String gender;//适用性别
    private String hospitaldate;//就诊期限
    private String hospitalroom;//就诊科室
    private String alarm;//预警等级
    private String othername;//其他名称
    private String medicalExplanation;//医学解释
    private String causes;//常见原因
    private String advice;//建议

    public Ill() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameOfDisease() {
        return nameOfDisease;
    }

    public void setNameOfDisease(String nameOfDisease) {
        this.nameOfDisease = nameOfDisease;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOccupational() {
        return occupational;
    }

    public void setOccupational(String occupational) {
        this.occupational = occupational;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHospitaldate() {
        return hospitaldate;
    }

    public void setHospitaldate(String hospitaldate) {
        this.hospitaldate = hospitaldate;
    }

    public String getHospitalroom() {
        return hospitalroom;
    }

    public void setHospitalroom(String hospitalroom) {
        this.hospitalroom = hospitalroom;
    }

    public String getAlarm() {
        return alarm;
    }

    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }

    public String getOthername() {
        return othername;
    }

    public void setOthername(String othername) {
        this.othername = othername;
    }

    public String getMedicalExplanation() {
        return medicalExplanation;
    }

    public void setMedicalExplanation(String medicalExplanation) {
        this.medicalExplanation = medicalExplanation;
    }

    public String getCauses() {
        return causes;
    }

    public void setCauses(String causes) {
        this.causes = causes;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }




    @Override
    public String toString() {
        return "Ill{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", nameOfDisease='" + nameOfDisease + '\'' +
                ", type='" + type + '\'' +
                ", occupational='" + occupational + '\'' +
                ", gender='" + gender + '\'' +
                ", hospitaldate='" + hospitaldate + '\'' +
                ", hospitalroom='" + hospitalroom + '\'' +
                ", alarm='" + alarm + '\'' +
                ", othername='" + othername + '\'' +
                ", medicalExplanation='" + medicalExplanation + '\'' +
                ", causes='" + causes + '\'' +
                ", advice='" + advice + '\'' +
                '}';
    }
}
