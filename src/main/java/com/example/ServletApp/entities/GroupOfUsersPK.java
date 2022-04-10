package com.example.ServletApp.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class GroupOfUsersPK implements Serializable {
    private Integer userId;
    private Integer groupId;

    @Column(name = "user_id", nullable = false)
    @Id
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "group_id", nullable = false)
    @Id
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

        GroupOfUsersPK that = (GroupOfUsersPK) o;

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
