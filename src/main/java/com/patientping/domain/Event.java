package com.patientping.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Jacob Eid on 11/23/2016.
 */
@Entity
@Table(name = "event")
@EntityListeners(AuditingEntityListener.class)
public class Event {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "event",fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<UserOrder> orders;

    @NotNull
    @Column(unique = true)
    private String eventName;

    private @CreatedDate
    Long createDate;

    public Long getCreateDate() {
        return createDate;
    }

    public Event(){
    }
    public Event(String eventName){
        this.eventName = eventName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public List<UserOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<UserOrder> orders) {
        this.orders = orders;
    }
}
