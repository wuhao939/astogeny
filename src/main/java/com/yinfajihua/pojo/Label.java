package com.yinfajihua.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Label  {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    @Transient
    private int browseTimes;
    @JsonIgnore
    private int deleteflag;
    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "labelSet")
    @JsonIgnoreProperties("labelSet")
    private Set<Info> infoSet;

    public Set<Info> getInfoSet() {
        return infoSet;
    }

    public void setInfoSet(Set<Info> infoSet) {
        this.infoSet = infoSet;
    }

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
