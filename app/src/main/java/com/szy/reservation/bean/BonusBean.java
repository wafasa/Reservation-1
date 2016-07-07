package com.szy.reservation.bean;

import java.io.Serializable;

public class BonusBean implements Serializable {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public BonusBean(String url, String title, String address, String price) {
        this.url = url;
        this.title = title;
        this.address = address;
        this.price = price;
    }

    public BonusBean() {
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;
    private String address;
    private String price;

    private String yd;
    private String wyd;

    public String getYd() {
        return yd;
    }

    public void setYd(String yd) {
        this.yd = yd;
    }

    public String getWyd() {
        return wyd;
    }

    public void setWyd(String wyd) {
        this.wyd = wyd;
    }
}
