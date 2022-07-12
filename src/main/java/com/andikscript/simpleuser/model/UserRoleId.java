package com.andikscript.simpleuser.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserRoleId implements Serializable {
    private static final long serialVersionUID = 8782181056271687688L;
    @Column(name = "id_user", nullable = false)
    private Integer idUser;

    @Column(name = "id_roles", nullable = false)
    private Integer idRoles;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdRoles() {
        return idRoles;
    }

    public void setIdRoles(Integer idRoles) {
        this.idRoles = idRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserRoleId entity = (UserRoleId) o;
        return Objects.equals(this.idUser, entity.idUser) &&
                Objects.equals(this.idRoles, entity.idRoles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idRoles);
    }

}