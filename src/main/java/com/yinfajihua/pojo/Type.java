package com.yinfajihua.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Type {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @Transient
    private int browseTimes;
    @JsonIgnore
    private int deleteflag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBrowseTimes() {
        return browseTimes;
    }

    public void setBrowseTimes(int browseTimes) {
        this.browseTimes = browseTimes;
    }

    public int getDeleteflag() {
        return deleteflag;
    }

    public void setDeleteflag(int deleteflag) {
        this.deleteflag = deleteflag;
    }
}
