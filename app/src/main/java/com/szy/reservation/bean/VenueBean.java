package com.szy.reservation.bean;

import java.io.Serializable;

public class VenueBean implements Serializable {
    private String time;
    private String date;
    private String number;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public VenueBean() {
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
