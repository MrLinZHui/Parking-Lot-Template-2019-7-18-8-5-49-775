package com.thoughtworks.parking_lot.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.thoughtworks.parking_lot.domain.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public ResponseEntity getParkingLotsWithPage(int page, int pagesize) {
        List<ParkingLot> parkingLotList = parkingLotRepository.findAll();
        JSONObject jsonpObject = new JSONObject();
        jsonpObject.put("page",page);
        jsonpObject.put("pagesize",pagesize);
        jsonpObject.put("parkingLots",parkingLotList.stream().skip((page-1)*pagesize).limit(pagesize).collect(Collectors.toList()));
        return ResponseEntity.ok(jsonpObject);
}

    public ResponseEntity getParkingLotById(long id) {
        return ResponseEntity.ok(parkingLotRepository.findById(id));
    }
}
