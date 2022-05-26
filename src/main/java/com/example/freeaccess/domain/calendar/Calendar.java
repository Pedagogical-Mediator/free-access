package com.example.freeaccess.domain.calendar;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    @NotNull
    @Column(nullable = false)
    private String url;

    public Calendar() {
    }

    public Calendar(Integer id, String url) {
        this.id = id;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
