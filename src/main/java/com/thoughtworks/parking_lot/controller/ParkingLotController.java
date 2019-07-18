package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.domain.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
@RequestMapping(path = "parkinglots")
public class ParkingLotController {

    private final Logger log = Logger.getLogger(this.getClass().getName());

    @Autowired
    ParkingLotService parkingLotService;

    @PostMapping
    public ResponseEntity saveParkLot(@RequestBody ParkingLot parkingLot){

        return parkingLotService.saveParkLot(parkingLot);
    }
}
