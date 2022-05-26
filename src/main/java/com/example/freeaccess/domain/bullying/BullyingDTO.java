package com.example.freeaccess.domain.bullying;

public class BullyingDTO {
    private Integer id;
    private String description;
    private String image;
    private String formURL;

    public BullyingDTO() {
    }

    public BullyingDTO(Integer id, String description, String image, String formURL) {
        this.id = id;
        this.description = description;
        this.image = image;
        this.formURL = formURL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFormURL() {
        return formURL;
    }

    public void setFormURL(String formURL) {
        this.formURL = formURL;
    }

    @Override
    public boolean equals(Object obj) {
        BullyingDTO bullying = (BullyingDTO) obj;
        return this.description.equals(bullying.getDescription()) && this.formURL.equals(bullying.getFormURL()) && this.image.equals(bullying.getImage());
    }
}
