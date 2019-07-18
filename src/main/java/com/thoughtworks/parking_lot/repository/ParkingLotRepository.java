package com.thoughtworks.parking_lot.repository;


import com.thoughtworks.parking_lot.domain.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {
    @Query(value = "delete from ParkingLot where id =?1")
    @Modifying
    void deleteByParkingLotId(long id);
}
