package com.example.ServletApp.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Userr {
    private Integer userId;
    private String login;
    private String password;
    private String email;
    private String nickName;

    public Userr(String login, String password, String email, String nickName) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.nickName = nickName;
    }

    public Userr() {
    }

    public Userr(Integer userId, String login, String password, String email, String nickName) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.email = email;
        this.nickName = nickName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "login", nullable = false, length = 50)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "nick_name", nullable = false, length = 50)
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Userr userr = (Userr) o;
        return Objects.equals(userId, userr.userId) && Objects.equals(login, userr.login) && Objects.equals(password, userr.password) && Objects.equals(email, userr.email) && Objects.equals(nickName, userr.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, login, password, email, nickName);
    }

    @Override
    public String toString() {
        return "Userr{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
