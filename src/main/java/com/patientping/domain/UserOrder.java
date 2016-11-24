package com.patientping.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Jacob Eid on 11/23/2016.
 */
@Entity
@Table(name = "userOrder")
@EntityListeners(AuditingEntityListener.class)
public class UserOrder {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private Integer numberOfTickets;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(targetEntity = Event.class)
    @JoinColumn(name = "event_id")
    @JsonBackReference
    private Event event;

    @LastModifiedDate
    private Long modifiedDate;

    @CreatedDate
    private Long createDate;


    public UserOrder(){}

    public UserOrder(Integer numberOfTickets,User user,Event event){
        this.numberOfTickets = numberOfTickets;
        this.user=user;
        this.event=event;
    }

    public Long getModifiedDate() {
        return modifiedDate;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }


    public Event getEvent(){
        return event;
    }

    public void setEvent(Event event){
        this.event = event;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }
}
