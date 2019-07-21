package com.thoughtworks.parking_lot.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "ParkingOrder")
public class ParkingOrder implements Serializable {
    @Id
    //@GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "parkinglotid")
    private ParkingLot parkingLot;

    @Column(name = "carNum")
    private String carNum;

    private String createDate;

    private String leaveDate;
    @Column
    private boolean isStatus;

    public ParkingOrder() {
    }

    public ParkingOrder(String carNum, String createDate, String leaveDate, ParkingLot parkingLot) {
        this.carNum = carNum;
        this.createDate = createDate;
        this.leaveDate = leaveDate;
        this.isStatus = true;
        this.parkingLot = parkingLot;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    public boolean getIsStatus() {
        return isStatus;
    }

    public void setIsStatus(boolean isStatus) {
        this.isStatus = isStatus;
    }
}
