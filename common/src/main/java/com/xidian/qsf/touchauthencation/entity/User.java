package com.xidian.qsf.touchauthencation.entity;

/**
 * Created by Qian Shaofeng on 2017/9/4.
 */

public class User {
    private String username;
    private String password;

    public User(){

    }

    public User(String name){
        username = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwd) {
        password = passwd;
    }
}
