package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.domain.ParkingOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParkingOrderRepository extends JpaRepository<ParkingOrder,Integer> {
    //@Query(value = "select * from ParkingOrder where parkinglotid =?1")
    List<ParkingOrder> findParkingOrdersByParkingLotId(long parkinglotid);
}
