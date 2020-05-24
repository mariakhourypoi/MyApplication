package com.example.myapplication;

import android.view.View;

public class Notification {
    private int notificationCount;
    private String name;
    private String birthday;
    private String homeAdress;
    private String schoolAdress;

    public Notification(String name,String birthday,String schoolAdress,String homeAdress) {
        this.name = name;
        this.birthday=birthday;
        this.schoolAdress=schoolAdress;
        this.homeAdress=homeAdress;
        notificationCount=0;
    }

    public Notification() {
        notificationCount=0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHomeAdress() {
        return homeAdress;
    }

    public String getSchoolAdress() {
        return schoolAdress;
    }

    public void setSchoolAdress(String schoolAdress) {
        this.schoolAdress = schoolAdress;
    }

    public void setHomeAdress(String homeAdress) {
        this.homeAdress = homeAdress;
    }

    public int getNotificationCount() {
        return notificationCount;
    }

    public void setNotificationCount(int notificationCount) {
        this.notificationCount = notificationCount;
    }
}
