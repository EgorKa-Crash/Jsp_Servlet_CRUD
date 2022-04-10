package com.example.ServletApp.entities;

import javax.persistence.*;

@Entity
@Table(name = "groupofusers")
@IdClass(GroupOfUsersPK.class)
//@Table(name = "groupofusers")
public class GroupOfUsers {
    private Integer userId;
    private Integer groupId;

    public GroupOfUsers() {
    }

    public GroupOfUsers(Integer userId, Integer groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }

    @Id
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "group_id", nullable = false)
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupOfUsers that = (GroupOfUsers) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        return result;
    }
}
