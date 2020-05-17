package com.xh.hospitalclient.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Registration extends RealmObject implements Serializable {
    @PrimaryKey
    private int regId;
    private String userId;
    private int schId;
    private int regRank;

    public Registration() {
    }

    public Registration(int regId, String userId, int schId, int regRank) {
        this.regId = regId;
        this.userId = userId;
        this.schId = schId;
        this.regRank = regRank;
    }

    public int getRegId() {
        return regId;
    }

    public void setRegId(int regId) {
        this.regId = regId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getSchId() {
        return schId;
    }

    public void setSchId(int schId) {
        this.schId = schId;
    }

    public int getRegRank() {
        return regRank;
    }

    public void setRegRank(int reg_rank) {
        this.regRank = regRank;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "regId=" + regId +
                ", userId='" + userId + '\'' +
                ", schId=" + schId +
                ", regRank=" + regRank +
                '}';
    }

}

