package com.LIGY.pojo;

import java.io.Serializable;

/**
 * 疾病库
 */
public class IllKonw implements Serializable {
    private Integer id;//id
    private String illname;//疾病名称
    private String keyword;//关键字
    private String description;//疾病说明
    private String advice;//建议


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIllname() {
        return illname;
    }

    public void setIllname(String illname) {
        this.illname = illname;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}
