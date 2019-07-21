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
        ParkingLot parkingLot = parkingLotRepository.findById(parkinglotid).get();
        parkingOrderRepository.save(new ParkingOrder("粤B_666666","2019-04-04:13:14","",parkingLot));
        List<ParkingOrder> orders = parkingOrderRepository.findParkingOrdersByParkingLotId(parkinglotid);
        if(parkingLot.getCapacity() > orders.size()){
            System.out.println("parkingLot.getCapacity()==========size:"+orders.size());
            Date current = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = sdf.format(current);
            ParkingOrder parkingOrder = parkingOrderRepository.save(new ParkingOrder(carNum,time,"",parkingLot));
            return ResponseEntity.ok(parkingOrder);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Massage","the capcity is full");
        return ResponseEntity.ok(jsonObject);
    }
    public ParkingOrder createOrder(long id){

        return null;
    }
}
