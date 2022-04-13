package com.example.ServletApp.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
public class Userr {

    @Id
    @GeneratedValue
    private Integer userId;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String nickName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true) //EAGER
    private List<Post> posts = new LinkedList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<GroupOfUsers> groupOfUsers = new LinkedList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Subscriptions> subscriptions = new LinkedList<>();

    @OneToMany(mappedBy = "subscriber", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Subscriptions> subscribers = new LinkedList<>();

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
}
