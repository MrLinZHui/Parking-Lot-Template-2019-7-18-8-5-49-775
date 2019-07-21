package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.domain.ParkingLot;
import com.thoughtworks.parking_lot.domain.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ParkingOrderService {
    @Autowired
    ParkingOrderRepository parkingOrderRepository;
    @Autowired
    ParkingLotRepository parkingLotRepository;

    public ResponseEntity addCar(long parkinglotid,String carNum) {
        JSONObject jsonObject = new JSONObject();
        ParkingLot parkingLot = parkingLotRepository.findById(parkinglotid).get();
        parkingOrderRepository.save(new ParkingOrder("粤B_666666","2019-04-04:13:14","",parkingLot));
        List<ParkingOrder> orders = parkingOrderRepository.findIsStatusIsTrueByParkingLotId(parkinglotid);
        if(parkingLot.getCapacity() > orders.size()){
            String time = getDateString();
            ParkingOrder parkingOrder = parkingOrderRepository.save(new ParkingOrder(carNum,time,"",parkingLot));
            return ResponseEntity.ok(parkingOrder);
        }

        jsonObject.put("Massage","the capcity is full");
        return ResponseEntity.ok(jsonObject);
    }
    @Transactional
    public ResponseEntity puteCar(String carNum) {
        ParkingOrder order = parkingOrderRepository.findBycarNum(carNum);
        String time = getDateString();
        order.setLeaveDate(time);
        order.setIsStatus(false);
        parkingOrderRepository.updateLeaveDateByCarNum(carNum,time);
        order = parkingOrderRepository.findBycarNum(carNum);
        return  ResponseEntity.ok(order);
    }

    private String getDateString() {
        Date current = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(current);
    }
}
