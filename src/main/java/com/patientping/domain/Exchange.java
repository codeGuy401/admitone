package com.patientping.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Jacob Eid on 11/23/2016.
 */
@Entity
@Table(name = "exchange")
@EntityListeners(AuditingEntityListener.class)
public class Exchange {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private int numberOfTickets;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @ManyToOne(targetEntity = Event.class)
    @JoinColumn(name = "event_from_id")
    @NotNull
    private Event eventFrom;

    @ManyToOne(targetEntity = Event.class)
    @JoinColumn(name = "event_to_id")
    @NotNull
    private Event eventTo;

    private @CreatedDate
    Long createDate;

    public Long getCreateDate() {
        return createDate;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEventFrom() {
        return eventFrom;
    }

    public void setEventFrom(Event eventFrom) {
        this.eventFrom = eventFrom;
    }

    public Event getEventTo() {
        return eventTo;
    }

    public void setEventTo(Event eventTo) {
        this.eventTo = eventTo;
    }
}
