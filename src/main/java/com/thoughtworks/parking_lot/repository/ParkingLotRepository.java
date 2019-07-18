package com.thoughtworks.parking_lot.repository;


import com.thoughtworks.parking_lot.domain.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {
    @Query(value = "delete from ParkingLot where id =?1")
    @Modifying
    void deleteByParkingLotId(long id);
    @Query(value = "update ParkingLot set capacity = ?2 where id =?1")
    @Modifying
    @Transactional
    int updateCapacityById(long id, int capacity);
}
