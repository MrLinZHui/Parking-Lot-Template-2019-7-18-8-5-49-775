package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.domain.ParkingOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ParkingOrderRepository extends JpaRepository<ParkingOrder,Integer> {
    //@Query(value = "select * from ParkingOrder where parkinglotid =?1 and isStatus = true")
    List<ParkingOrder> findIsStatusIsTrueByParkingLotId(long parkinglotid);

    ParkingOrder findBycarNum(String carNum);
    @Transactional
    @Query(value = "update ParkingOrder set leaveDate =?2,isStatus =false where carNum = ?1")
    @Modifying
    void updateLeaveDateByCarNum(String carNum, String time);
}
