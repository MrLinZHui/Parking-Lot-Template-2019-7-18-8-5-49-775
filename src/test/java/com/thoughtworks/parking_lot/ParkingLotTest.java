package com.thoughtworks.parking_lot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.parking_lot.domain.ParkingLot;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingLotTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_add_a_parkinglot_when_post_a_new_parkinglot()throws Exception{
        //given
        ParkingLot parkingLot = new ParkingLot("parkingLot1",10,"NanRuan");
        this.mockMvc.perform(post("/parkinglots") .content(new ObjectMapper().writeValueAsString(parkingLot))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void should_delete_a_parkinglot_when_delete_a_new_parkinglot()throws Exception{
        //given
        ParkingLot parkingLot = new ParkingLot("parkingLot2",10,"NanRuan");
        MvcResult mvcResult = this.mockMvc.perform(post("/parkinglots") .content(new ObjectMapper().writeValueAsString(parkingLot))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        this.mockMvc.perform(delete("/parkinglots/"+jsonObject.getInt("id"))).andExpect(status().isOk());


    }
    @Test
    public void should_get_parkinglots_when_give_a_size_page_and_pagesize()throws Exception{
        //given
        ParkingLot parkingLot = new ParkingLot("parkingLot3",10,"NanRuan");
        this.mockMvc.perform(post("/parkinglots") .content(new ObjectMapper().writeValueAsString(parkingLot))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        ParkingLot parkingLot1 = new ParkingLot("parkingLot4",10,"NanRuan");
        this.mockMvc.perform(post("/parkinglots") .content(new ObjectMapper().writeValueAsString(parkingLot1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        MvcResult mvcResult = this.mockMvc.perform(get("/parkinglots?page=1&pagesize=15")).andExpect(status().isOk()).andReturn();
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals(1, jsonObject.getInt("page"));
        assertEquals(15, jsonObject.getInt("pagesize"));
        //assertEquals(2, jsonObject.getJSONArray("parkingLots").length());
    }
    @Test
    public void should_get_a_parkinglot_when_give_a_id()throws Exception{
        //given
        ParkingLot parkingLot = new ParkingLot("parkingLot5",10,"NanRuan");
        MvcResult mvcResult = this.mockMvc.perform(post("/parkinglots") .content(new ObjectMapper().writeValueAsString(parkingLot))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        JSONObject jsonObject1 = new JSONObject(mvcResult.getResponse().getContentAsString());
        mvcResult = this.mockMvc.perform(get("/parkinglots/"+jsonObject1.getInt("id"))).andExpect(status().isOk()).andReturn();
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals(parkingLot.getName(), jsonObject.getString("name"));
        assertEquals(parkingLot.getCapacity(), jsonObject.getInt("capacity"));
        assertEquals(parkingLot.getLocaltion(), jsonObject.getString("localtion"));
        //assertEquals(2, jsonObject.getJSONArray("parkingLots").length());
    }
    @Test
    public void should_get_a_update_parkinglot_when_give_update_a_parkingLot()throws Exception{
        //given
        ParkingLot parkingLot = new ParkingLot("parkingLot6",10,"NanRuan");
        MvcResult mvcResult = this.mockMvc.perform(post("/parkinglots").content(new ObjectMapper().writeValueAsString(parkingLot))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        JSONObject jsonObject1 = new JSONObject(mvcResult.getResponse().getContentAsString());
        ParkingLot parkingLot1 = new ParkingLot("parkingLot6",25,"NanRuan");
        System.out.println("======="+jsonObject1.getInt("id"));
        mvcResult =this.mockMvc.perform(put("/parkinglots/"+jsonObject1.getInt("id")).content(new ObjectMapper().writeValueAsString(parkingLot1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals(1, jsonObject.getInt("success"));
    }
//    @Test
//    public void should_return_exception_when_post_two_parkinglot_have_save_name()throws Exception{
//        //given
//        ParkingLot parkingLot = new ParkingLot("parkingLot1",10,"NanRuan");
//        this.mockMvc.perform(post("/parkinglots") .content(new ObjectMapper().writeValueAsString(parkingLot))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated());
//        ParkingLot parkingLot1 = new ParkingLot("parkingLot1",20,"NanRuan");
//        this.mockMvc.perform(post("/parkinglots") .content(new ObjectMapper().writeValueAsString(parkingLot1))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)));
//    }

}
