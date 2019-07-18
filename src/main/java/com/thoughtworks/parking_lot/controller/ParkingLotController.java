package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.domain.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping(path = "/parkinglots")
public class ParkingLotController {

    private final Logger log = Logger.getLogger(this.getClass().getName());

    @Autowired
    ParkingLotService parkingLotService;

    @PostMapping
    public ResponseEntity saveParkLot(@RequestBody ParkingLot parkingLot){

        return parkingLotService.saveParkLot(parkingLot);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteParkLot(@PathVariable long id){
        return parkingLotService.deleteParkLot(id);
    }
    @GetMapping(params = {"page","pagesize"})
    public ResponseEntity getParkingLotsWithPage(@RequestParam(required = false)int page,
                                         @RequestParam(required = false)int pagesize){
        return parkingLotService.getParkingLotsWithPage(page,pagesize);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity getParkingLotById(@PathVariable long id){
        return parkingLotService.getParkingLotById(id);
    }
}
