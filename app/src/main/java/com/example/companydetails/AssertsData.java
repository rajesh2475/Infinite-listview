package com.example.companydetails;

public class AssertsData {
    private String status;
    private String imgURL;
    private String name;
    public AssertsData(String status, String imgURL, String name) {
        this.status = status;
        this.imgURL = imgURL;
        this.name = name;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
