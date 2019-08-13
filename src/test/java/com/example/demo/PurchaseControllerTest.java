package com.example.demo;

import com.example.demo.controller.PurchasesController;
import com.example.demo.entity.Purchase;
import com.example.demo.service.PurchaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(PurchasesController.class)
public class PurchaseControllerTest {


//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private PurchaseService service;
//
//    @Test
//    public void testAddPurchase(){
//
//        String json = "{\"name\": \"TestName\", \"dateFrom\": \"2019-08-20\", \"dateTo\": \"2019-08-20\"}";
//
//        try {
//            mvc.perform(post("purchase").contentType(MediaType.APPLICATION_JSON).content(json));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        Purchase purchase = service.findByName("TestName").get(0);
//
//        assertThat(purchase.getDateFrom()).isEqualTo(Date.valueOf("2019-08-20"));
//    }
}
