package com.yinfajihua.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Info implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String abstracts;
    private String uploadTime;
    @Transient
    private int browseTimes;
    private String contexts;
    private int likeCount;
    private String url;
    @JsonIgnore
    private int deleteflag;
    @ManyToOne
    @JoinColumn(name = "type_id",referencedColumnName = "id")
    private Type type;
    @ManyToOne
    @JoinColumn(name = "author_id",referencedColumnName = "id")
    private Author author;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "info_label",joinColumns = @JoinColumn(name = "info_id")
            ,inverseJoinColumns = @JoinColumn(name = "label_id"))
    @JsonIgnoreProperties("infoSet")
    private Set<Label> labelSet;





    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public Set<Label> getLabelSet() {
        return labelSet;
    }

    public void setLabelSet(Set<Label> labelSet) {
        this.labelSet = labelSet;
    }

    public Author getAuthor(){
        return author;
    }
    public void setAuthor(Author author){
        this.author = author;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getContexts() {
        return contexts;
    }

    public void setContexts(String contexts) {
        this.contexts = contexts;
    }
    @Transient
    public int getBrowseTimes() {
        return browseTimes;
    }

    public void setBrowseTimes(int browseTimes) {
        this.browseTimes = browseTimes;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDeleteflag() {
        return deleteflag;
    }

    public void setDeleteflag(int deleteflag) {
        this.deleteflag = deleteflag;
    }
}
