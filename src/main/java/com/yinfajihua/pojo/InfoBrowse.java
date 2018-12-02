package com.yinfajihua.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class InfoBrowse {
    @Id
    @GeneratedValue
    private int id;
    private int info_id;
    private int user_id;
    private String time;
    @JsonIgnore
    private int deleteflag;

    public InfoBrowse(int info_id,int user_id,String time){
        this.info_id = info_id;
        this.user_id = user_id;
        this.time = time;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInfo_id() {
        return info_id;
    }

    public void setInfo_id(int info_id) {
        this.info_id = info_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getDeleteflag() {
        return deleteflag;
    }

    public void setDeleteflag(int deleteflag) {
        this.deleteflag = deleteflag;
    }
}
