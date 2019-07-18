package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.domain.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotService {

    @Autowired
    ParkingLotRepository parkingLotRepository;
    public ResponseEntity saveParkLot(ParkingLot parkingLot) {
        parkingLotRepository.saveAndFlush(parkingLot);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
