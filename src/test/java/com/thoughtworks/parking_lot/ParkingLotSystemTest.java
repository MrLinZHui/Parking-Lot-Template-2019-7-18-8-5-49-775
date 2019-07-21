package com.thoughtworks.parking_lot;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.parking_lot.domain.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingLotSystemTest {
    @Autowired
    private ParkingOrderRepository repository;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private MockMvc mockMvc;
    private  ParkingLot parkingLot1;
    @Before
    public void saveData(){
        repository.deleteAll();
        parkingLotRepository.deleteAll();
    }
    @Test
    public void shoule_return_a_order_when_park_a_car_Into_parikingLot_without_full_capacity()throws Exception{
        //given

        ParkingLot parkingLot = new ParkingLot("parkingLot1",10,"NanRuan");
        parkingLot1 = parkingLotRepository.saveAndFlush(parkingLot);
        //when
        MvcResult mvcResult = this.mockMvc.perform(post("/parkingorders/"+parkingLot1.getId()+"?carNum=粤B_666666"))
                .andExpect(status().isOk()).andReturn();
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        //then
        assertEquals(parkingLot.getId(), jsonObject.getJSONObject("parkingLot").getInt("id"));
        assertEquals("粤B_666666", jsonObject.getString("carNum"));

    }
 @Test
    public void shoule_returncapacity_is_full_when_park_a_car_Into_parikingLot_with_full_capacity()throws Exception{
        //given
        ParkingLot parkingLot = new ParkingLot("parkingLot1",1,"NanRuan");
        parkingLot1 = parkingLotRepository.saveAndFlush(parkingLot);
        this.mockMvc.perform(post("/parkingorders/"+parkingLot1.getId()+"?carNum=粤B888888"));
        //when
        MvcResult mvcResult = this.mockMvc.perform(post("/parkingorders/"+parkingLot1.getId()+"?carNum=粤B666666"))
                .andExpect(status().isOk()).andReturn();
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        //then
        assertEquals("the capcity is full", jsonObject.getString("Massage"));
    }
@Test
    public void shoule_return_a_leave_order_when_car_leave()throws Exception{
        //given
        ParkingLot parkingLot = new ParkingLot("parkingLot1",10,"NanRuan");
        parkingLot1 = parkingLotRepository.saveAndFlush(parkingLot);
        this.mockMvc.perform(post("/parkingorders/"+parkingLot1.getId()+"?carNum=粤B888888"));
        this.mockMvc.perform(post("/parkingorders/"+parkingLot1.getId()+"?carNum=粤B666666"));
        //when
        MvcResult mvcResult = this.mockMvc.perform(get("/parkingorders?carNum=粤B666666"))
                .andExpect(status().isOk()).andReturn();
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        //then

        Assertions.assertNotNull(jsonObject.getString("leaveDate"));
        assertEquals("false", jsonObject.getString("isStatus"));
    }

}
