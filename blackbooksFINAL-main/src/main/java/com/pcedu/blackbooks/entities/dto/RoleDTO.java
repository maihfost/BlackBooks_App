package com.pcedu.blackbooks.entities.dto;

import com.pcedu.blackbooks.entities.Role;

public class RoleDTO {

    private Integer id;
    private String name;

    public RoleDTO() {
    }

    public RoleDTO(Role role) {
        this.id = role.getId();
        this.name = role.getName();
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
        sb.append("RoleDTO{id=").append(id);
        sb.append(", name=").append(name);
        sb.append('}');
        return sb.toString();
    }

}
