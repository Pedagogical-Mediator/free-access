package com.example.freeaccess.domain.news;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class NewsDTO implements Serializable {
    private Integer id;
    private String title;
    private String description;
    private String urls;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date creationDate;
    private Boolean notifiable;

    public Boolean isNotificavel() {
        return notifiable;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getNotifiable() {
        return notifiable;
    }

    public void setNotifiable(Boolean notifiable) {
        this.notifiable = notifiable;
    }
}
