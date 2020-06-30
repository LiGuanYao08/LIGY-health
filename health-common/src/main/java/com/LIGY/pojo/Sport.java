package com.LIGY.pojo;

import java.io.Serializable;

/**
 * 运动知识库
 */
public class Sport implements Serializable {
    private Integer id;//主健
    private String code;//编号
    private String namesport;//运动名称
    private String intensity;//运动强度
    private String quantity;//运动量
    private String time;//运动时间
    private String age;//适用年龄

    public Sport(Integer id, String code, String namesport, String intensity, String quantity, String time, String age){
        this.id = id;
        this.code = code;
        this.namesport = namesport;
        this.intensity = intensity;
        this.quantity = quantity;
        this.time = time;
        this.age = age;
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

    public String getNamesport() {
        return namesport;
    }

    public void setNamesport(String namesport) {
        this.namesport = namesport;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


}
