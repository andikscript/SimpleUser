package com.andikscript.simpleuser.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id_user", nullable = false)
    private Integer id;

    @Column(name = "nama", nullable = false, length = 256)
    private String nama;

    @Column(name = "email", nullable = false, length = 256)
    private String email;

    @Column(name = "username", nullable = false, length = 256)
    private String username;

    @Column(name = "password", nullable = false, length = 2048)
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}