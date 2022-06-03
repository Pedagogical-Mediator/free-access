package com.example.freeaccess.domain.notice;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
//@EntityListeners(AvisoListener.class)
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotNull
    private String title;

    @NotNull
    @Column(nullable = false, length = 300)
    @Lob
    @Size(max = 300)
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "notice_urls", joinColumns = @JoinColumn(name = "notice_id"), foreignKey = @ForeignKey(name = "urls_notice_fk"))
    private List<Url> urls = new ArrayList<>();


    @Column(nullable = false, columnDefinition = "DATE")
    @NotNull
    private LocalDate creationDate;

    @Lob
    private String image;

    public Notice() {
    }

    public Notice(Integer id, String title, String description, List<Url> urls, String image, LocalDate creationDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.urls = urls;
        this.image = image;
        this.creationDate = creationDate;
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

    public List<Url> getUrls() {
        return urls;
    }

    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

