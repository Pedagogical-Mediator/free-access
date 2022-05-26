package com.example.freeaccess.domain.bullying;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Bullying {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Lob
    @NotNull
    private String description;

    @Column
    @Lob
    private String image;

    @NotNull
    @Column(nullable = false)
    private String formURL;

    public Bullying() {
    }

    public Bullying(Integer id, String description, String image, String formURL) {
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

    public void setDescription(String descricao) {
        this.description = descricao;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String imagem) {
        this.image = imagem;
    }

    public String getFormURL() {
        return formURL;
    }

    public void setFormURL(String linkDoFormulario) {
        this.formURL = linkDoFormulario;
    }
}
