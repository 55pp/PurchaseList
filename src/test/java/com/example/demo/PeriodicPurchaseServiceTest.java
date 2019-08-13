package com.example.demo;

import com.example.demo.entity.PeriodicPurchase;
import com.example.demo.service.PeriodicPurchaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PeriodicPurchaseServiceTest {

    @Autowired
    private PeriodicPurchaseService periodicPurchaseService;

    @Test
    public void testCRUD(){
        List<PeriodicPurchase> listFirst = periodicPurchaseService.findAll();

        PeriodicPurchase periodicPurchase = new PeriodicPurchase();
        periodicPurchase.setName("TestName");
        periodicPurchase.setDateLast(Date.valueOf(LocalDate.now()));
        periodicPurchase.setNumdays((long)5);
        periodicPurchaseService.createPeriod(periodicPurchase);

        List<PeriodicPurchase> listSecond = periodicPurchaseService.findAll();

        assertThat(listSecond.size()).isEqualTo(listFirst.size() + 1);

        assertThat(listSecond.stream().anyMatch(o -> o.getName().equals("TestName"))).isEqualTo(true);
        PeriodicPurchase periodicPurchase1 = listSecond.stream().filter(o -> o.getName().equals("TestName")).findFirst().get();

        assertThat(periodicPurchase.getId()).isEqualTo(periodicPurchase1.getId());

        periodicPurchase1.setName("NewTestName");
        periodicPurchaseService.updatePeriod(periodicPurchase1);

        PeriodicPurchase periodicPurchase2 = periodicPurchaseService.findById(periodicPurchase1.getId());
        assertThat(periodicPurchase2.getName()).isEqualTo("NewTestName");

        periodicPurchaseService.deletePeriodById(periodicPurchase1.getId());
    }

    @Test
    public void testFindUnplannedPurchase(){
        PeriodicPurchase periodicPurchase1 = new PeriodicPurchase();
        periodicPurchase1.setName("TestName1");
        periodicPurchase1.setDateLast(Date.valueOf(LocalDate.now()));
        periodicPurchase1.setNumdays((long)5);
        periodicPurchaseService.createPeriod(periodicPurchase1);

        PeriodicPurchase periodicPurchase2 = new PeriodicPurchase();
        periodicPurchase2.setName("TestName2");
        periodicPurchase2.setDateLast(Date.valueOf(LocalDate.now().plusDays(1)));
        periodicPurchase2.setNumdays((long)5);
        periodicPurchaseService.createPeriod(periodicPurchase2);

        List<PeriodicPurchase> list = periodicPurchaseService.findUnplannedPurchase(Date.valueOf(LocalDate.now()));

        assertThat(list.stream().anyMatch(o -> o.getName().equals("TestName1"))).isEqualTo(true);
        assertThat(list.stream().anyMatch(o -> o.getName().equals("TestName2"))).isEqualTo(false);

        for(PeriodicPurchase period: list){
            periodicPurchaseService.deletePeriodById(period.getId());
        }
    }
}
