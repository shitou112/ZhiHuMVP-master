package com.xidian.qsf.touchauthencation.entity;

import java.util.Date;

/**
 * Created by Qian Shaofeng on 2017/9/7.
 */

public class ClickData {
    private static String uid;
    private double pointX;
    private double pointY;
    private double timestamp;

    private Date click_time;

    public ClickData(String username){
        this.uid = username;
    }

    public Date getClick_time() {
        return click_time;
    }

    public void setClick_time(Date click_time) {
        this.click_time = click_time;
    }

    public double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(double timestamp) {
        this.timestamp = timestamp;
    }

    public static String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public double getPointX() {
        return pointX;
    }

    public void setPointX(double pointX) {
        this.pointX = pointX;
    }

    public double getPointY() {
        return pointY;
    }

    public void setPointY(double pointY) {
        this.pointY = pointY;
    }

    public static ClickData setValues(String uid, double[] feature){

        ClickData userClickData = new ClickData(uid);
        userClickData.setPointX(feature[0]);
        userClickData.setPointY(feature[1]);
        userClickData.setTimestamp(feature[2]);

        return userClickData;
    }
}
