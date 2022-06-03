package com.example.freeaccess.domain.notice;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Url implements Serializable {
    @Column
    private String url;

    @Column
    private String name;

    public Url() {
    }

    public Url(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        Url url = (Url) obj;
        return this.url.equals(url.url) && this.name.equals(url.name);
    }
}
