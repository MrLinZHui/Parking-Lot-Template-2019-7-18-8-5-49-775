package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.domain.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ParkingLotService {

    @Autowired
    ParkingLotRepository parkingLotRepository;
    @Transactional
    public ResponseEntity saveParkLot(ParkingLot parkingLot) {
        parkingLotRepository.saveAndFlush(parkingLot);
        return ResponseEntity.ok(parkingLot);
    }
    @Transactional
    public ResponseEntity deleteParkLot(long id) {
        parkingLotRepository.deleteByParkingLotId(id);
        return ResponseEntity.ok().build();
    }
    public ResponseEntity getParkingLots() {
        List<ParkingLot> parkingLotList = parkingLotRepository.findAll();
        return ResponseEntity.ok(parkingLotList);
    }
}
