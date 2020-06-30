package com.LIGY.pojo;

import java.io.Serializable;

public class Company implements Serializable {
    private Integer id;
    private String companyAddress;
    private String longitudu;
    private String latitudu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getLongitudu() {
        return longitudu;
    }

    public void setLongitudu(String longitudu) {
        this.longitudu = longitudu;
    }

    public String getLatitudu() {
        return latitudu;
    }

    public void setLatitudu(String latitudu) {
        this.latitudu = latitudu;
    }
}
