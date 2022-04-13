package com.example.ServletApp.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class SubscriptionsPK implements Serializable{
    private Userr user;
    private Userr subscriber;

    public SubscriptionsPK(Userr user, Userr subscriber) {
        this.user = user;
        this.subscriber = subscriber;
    }

    public SubscriptionsPK() {
    }
}

