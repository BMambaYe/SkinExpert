package com.zhanghao.skinexpert.fragmenme_activity.bean;

/**
 * Created by RockGao on 2016/12/21.
 */

public class LocationDataBean {
    private String name;
    private String phoneNum;
    private String locationdetails;

    public LocationDataBean() {
    }

    public LocationDataBean(String name, String locationdetails, String phoneNum) {
        this.name = name;
        this.locationdetails = locationdetails;
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getLocationdetails() {
        return locationdetails;
    }

    public void setLocationdetails(String locationdetails) {
        this.locationdetails = locationdetails;
    }
}
