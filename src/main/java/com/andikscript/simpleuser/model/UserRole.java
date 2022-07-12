package com.andikscript.simpleuser.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private UserRoleId id;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @MapsId("idUser")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_user", nullable = false)
    private User idUser;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @MapsId("idRoles")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_roles", nullable = false)
    private Role idRoles;

    public UserRoleId getId() {
        return id;
    }

    public void setId(UserRoleId id) {
        this.id = id;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Role getIdRoles() {
        return idRoles;
    }

    public void setIdRoles(Role idRoles) {
        this.idRoles = idRoles;
    }

}