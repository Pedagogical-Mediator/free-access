package com.example.freeaccess.domain.news;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(nullable = false, length = 300)
    @Lob
    @Size(max = 300)
    private String title;

    @Lob
    @Column
    private String description;

    @NotNull
    @Column(nullable = false)
    private String urls;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate creationDate;

    @NotNull
    @Column(nullable = false)
    private boolean notifiable;

    public boolean isNotifiable() {
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setNotifiable(boolean notifiable) {
        this.notifiable = notifiable;
    }
}
