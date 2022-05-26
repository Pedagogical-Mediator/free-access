package com.example.freeaccess.domain.student_union;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class StudentUnion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    private String image;

    private String url;

    @Lob
    @Column(nullable = false)
    @NotNull
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String link) {
        this.url = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
