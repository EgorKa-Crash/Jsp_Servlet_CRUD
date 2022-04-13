package com.example.ServletApp.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@Entity
@IdClass(SubscriptionsPK.class)
public class Subscriptions {

    @Id
    @ManyToOne
    @JoinColumn(name = "userId",  nullable = false)
    private Userr user;

    @Id
    @ManyToOne
    @JoinColumn(name = "subscriberId",  nullable = false)
    private Userr subscriber;

    private Timestamp subscriptionDate;

    public Subscriptions() {
    }

    public Subscriptions(Userr user, Userr subscriber) {
        this.user = user;
        this.subscriber = subscriber;
    }

    public Subscriptions(Userr user, Userr subscriber, Timestamp subscriptionDate) {
        this.user = user;
        this.subscriber = subscriber;
        this.subscriptionDate = subscriptionDate;
    }
}