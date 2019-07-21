package com.thoughtworks.parking_lot.domain;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@DynamicInsert(true)
@Table(name = "ParkingLot")
public class ParkingLot implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(unique = true)
    private String name;

    private int capacity;

    private String localtion;

    public ParkingLot() {

    }

    public ParkingLot(String name, int capacity, String localtion) {
        this.name = name;
        this.capacity = capacity;
        this.localtion = localtion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLocaltion() {
        return localtion;
    }

    public void setLocaltion(String localtion) {
        this.localtion = localtion;
    }


}
