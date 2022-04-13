package com.example.ServletApp.entities;

import lombok.Data;

import java.io.Serializable;


@Data
public class GroupOfUsersPK implements Serializable {

    private Userr user;
    private Groupp group;

    public GroupOfUsersPK() {
    }

    public GroupOfUsersPK(Userr user, Groupp group) {
        this.user = user;
        this.group = group;
    }
}
