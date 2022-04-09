package com.example.ServletApp.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Groupp {
    private Integer groupId;
    private String groupName;
    private Timestamp creatingDate;
    private String groupComment;

    public Groupp() {
    }

    public Groupp(  String groupName, String groupComment) {
        this.groupName = groupName;
        this.groupComment = groupComment;
    }

    public Groupp(Integer groupId, String groupName,   String groupComment) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupComment = groupComment;
    }

    public Groupp(Integer groupId, String groupName, Timestamp creatingDate, String groupComment) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.creatingDate = creatingDate;
        this.groupComment = groupComment;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", nullable = false)
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "group_name", nullable = false, length = 50)
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Basic
    @Column(name = "creating_date", nullable = true)
    public Timestamp getCreatingDate() {
        return creatingDate;
    }

    public void setCreatingDate(Timestamp creatingDate) {
        this.creatingDate = creatingDate;
    }

    @Basic
    @Column(name = "group_comment", nullable = true, length = 500)
    public String getGroupComment() {
        return groupComment;
    }

    public void setGroupComment(String groupComment) {
        this.groupComment = groupComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Groupp groupp = (Groupp) o;

        if (groupId != null ? !groupId.equals(groupp.groupId) : groupp.groupId != null) return false;
        if (groupName != null ? !groupName.equals(groupp.groupName) : groupp.groupName != null) return false;
        if (creatingDate != null ? !creatingDate.equals(groupp.creatingDate) : groupp.creatingDate != null)
            return false;
        if (groupComment != null ? !groupComment.equals(groupp.groupComment) : groupp.groupComment != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = groupId != null ? groupId.hashCode() : 0;
        result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        result = 31 * result + (creatingDate != null ? creatingDate.hashCode() : 0);
        result = 31 * result + (groupComment != null ? groupComment.hashCode() : 0);
        return result;
    }
}
