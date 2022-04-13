package com.example.ServletApp.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@IdClass(GroupOfUsersPK.class)
public class GroupOfUsers {

    @Id
    @ManyToOne
    @JoinColumn(name = "userId",  nullable = false)
    private Userr user;

    @Id
    @ManyToOne
    @JoinColumn(name = "groupId",  nullable = false)
    private Groupp group;

    public GroupOfUsers() {
    }

    public GroupOfUsers(Userr user, Groupp group) {
        this.user = user;
        this.group = group;
    }
}