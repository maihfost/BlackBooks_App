package com.pcedu.blackbooks.entities.dto;

import com.pcedu.blackbooks.entities.Categorie;

public class CategorieDTO {

     private Integer id;
    private String name;

    public CategorieDTO() {
    }

    public CategorieDTO(Categorie categorie) {
        this.id = categorie.getId();
        this.name = categorie.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CategorieDTO{id=").append(id);
        sb.append(", name=").append(name);
        sb.append('}');
        return sb.toString();
    }

}
