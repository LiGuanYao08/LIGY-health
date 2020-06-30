package com.LIGY.pojo;

import java.io.Serializable;

public class BMap implements Serializable{
    private Integer id;
    private String name;
    private String xoffse;
    private String yoffse;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXoffse() {
        return xoffse;
    }

    public void setXoffse(String xoffse) {
        this.xoffse = xoffse;
    }

    public String getYoffse() {
        return yoffse;
    }

    public void setYoffse(String yoffse) {
        this.yoffse = yoffse;
    }
}
