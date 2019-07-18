package com.thoughtworks.parking_lot.repository;


import com.thoughtworks.parking_lot.domain.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, Integer> {
}
