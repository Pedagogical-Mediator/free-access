package com.example.freeaccess.domain.notice;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


public class NoticeDTO implements Serializable {

    private Integer id;
    private String title;
    private String description;
    private List<Url> urls;
    @JsonFormat(pattern="dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate creationDate;
    private String image;

    public NoticeDTO() {
    }

    public NoticeDTO(Integer id, String title, String description, List<Url> urls, LocalDate creationDate, String image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.urls = urls;
        this.creationDate = creationDate;
        this.image = image;
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

    public List<Url> getUrls() {
        return urls;
    }

    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object obj) {
        NoticeDTO other = (NoticeDTO) obj;
        return this.image.equals(other.getImage())
                && this.title.equals(other.getTitle())
                && this.urls.equals(other.getUrls())
                && this.creationDate.equals(other.getCreationDate())
                && this.description.equals(other.getDescription());
    }

    @Override
    public String toString() {
        return this.image + this.title + this.urls;
    }
}