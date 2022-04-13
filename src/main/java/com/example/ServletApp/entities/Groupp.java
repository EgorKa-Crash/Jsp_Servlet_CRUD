package com.example.ServletApp.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
public class Groupp {

    @Id
    @GeneratedValue
    private Integer groupId;

    @Column(nullable = false, unique = true)
    private String groupName;

    @Column(nullable = false)
    private Timestamp creatingDate;

    @Column(length = 500)
    private String groupComment;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<GroupOfUsers> groupOfUsers = new LinkedList<>();

    public Groupp() {
    }

    public Groupp(  String groupName, String groupComment) {
        this.groupName = groupName;
        this.groupComment = groupComment;
    }


    public Groupp(Integer groupId, String groupName, Timestamp creatingDate, String groupComment) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.creatingDate = creatingDate;
        this.groupComment = groupComment;
    }
}
