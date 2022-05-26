package com.example.freeaccess.domain.student_union;

public class StudentUnionDTO {
    private Integer id;
    private String url;
    private String image;
    private String description;

    public StudentUnionDTO() {
    }

    public StudentUnionDTO(Integer id, String url, String image, String description) {
        this.id = id;
        this.url = url;
        this.image = image;
        this.description = description;
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

    @Override
    public boolean equals(Object obj) {
        StudentUnionDTO studentUnionDTO = (StudentUnionDTO) obj;
        return this.description.equals(studentUnionDTO.getDescription()) && this.image.equals(studentUnionDTO.getImage()) && this.url.equals(studentUnionDTO.getUrl());
    }
}
