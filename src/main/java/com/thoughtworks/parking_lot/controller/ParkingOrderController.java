package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.service.ParkingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;
@Controller
@RequestMapping(path = "/parkingorders")
public class ParkingOrderController {
    private final Logger log = Logger.getLogger(this.getClass().getName());

    @Autowired
    ParkingOrderService parkingOrderService;

    @PostMapping(path = "/{lotid}")
    public ResponseEntity addCar(@PathVariable long lotid,@RequestParam(required = false) String carNum){
        System.out.println("========================================");
        return parkingOrderService.addCar(lotid,carNum);
    }
}
