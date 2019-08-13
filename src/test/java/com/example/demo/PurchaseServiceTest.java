package com.example.demo;

import com.example.demo.entity.Purchase;
import com.example.demo.service.PurchaseService;
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
public class PurchaseServiceTest {

    @Autowired
    private PurchaseService purchaseService;

    @Test
    public void testGetByName(){
        Purchase testPurchase = new Purchase();
        testPurchase.setName("TestName");
        testPurchase.setDateFrom(Date.valueOf(LocalDate.now()));
        testPurchase.setDateTo(Date.valueOf(LocalDate.now()));
        purchaseService.createPurchase(testPurchase);

        Purchase purchase = purchaseService.findByName("TestName").get(0);

        assertThat(purchase.getName()).isEqualTo("TestName");
        assertThat(purchase.getDateFrom()).isEqualTo(Date.valueOf(LocalDate.now()));
        assertThat(purchase.getDateTo()).isEqualTo(Date.valueOf(LocalDate.now()));
        assertThat(purchase.getDateWhen()).isEqualTo(null);

        purchaseService.deletePurchaseById(purchase.getId());
    }

    @Test
    public void testGetById(){
        Purchase testPurchase = new Purchase();
        testPurchase.setName("TestName");
        testPurchase.setDateFrom(Date.valueOf(LocalDate.now()));
        testPurchase.setDateTo(Date.valueOf(LocalDate.now()));
        purchaseService.createPurchase(testPurchase);

        Purchase purchaseFirst = purchaseService.findByName("TestName").get(0);
        Purchase purchaseSecond = purchaseService.findById(purchaseFirst.getId());

        assertThat(purchaseFirst.getName()).isEqualTo(purchaseSecond.getName());
        assertThat(purchaseFirst.getDateFrom()).isEqualTo(purchaseSecond.getDateFrom());
        assertThat(purchaseFirst.getDateTo()).isEqualTo(purchaseSecond.getDateTo());
        assertThat(purchaseFirst.getDateWhen()).isEqualTo(purchaseSecond.getDateWhen());

        purchaseService.deletePurchaseById(purchaseFirst.getId());
    }

    @Test
    public void testUpdatePurchase(){
        Purchase testPurchase = new Purchase();
        testPurchase.setName("TestName");
        testPurchase.setDateFrom(Date.valueOf(LocalDate.now()));
        testPurchase.setDateTo(Date.valueOf(LocalDate.now()));
        purchaseService.createPurchase(testPurchase);

        Purchase purchaseFirst = purchaseService.findByName("TestName").get(0);
        purchaseFirst.setName("NewTestName");
        purchaseService.updatePurchase(purchaseFirst);

        Purchase purchaseSecond = purchaseService.findByName("NewTestName").get(0);

        assertThat(purchaseFirst.getId()).isEqualTo(purchaseSecond.getId());

        purchaseService.deletePurchaseById(purchaseSecond.getId());
    }

    @Test
    public void testFindAllByName(){
        Purchase testPurchase1 = new Purchase();
        testPurchase1.setName("TestName");
        testPurchase1.setDateFrom(Date.valueOf(LocalDate.now()));
        testPurchase1.setDateTo(Date.valueOf(LocalDate.now()));
        purchaseService.createPurchase(testPurchase1);

        Purchase testPurchase2 = new Purchase();
        testPurchase2.setName("TestName");
        testPurchase2.setDateFrom(Date.valueOf(LocalDate.now()));
        testPurchase2.setDateTo(Date.valueOf(LocalDate.now()));
        purchaseService.createPurchase(testPurchase2);

        List<Purchase> list = purchaseService.findAllByName("TestName");

        assertThat(list.size()).isEqualTo(2);

        for(Purchase purchase: list){
            purchaseService.deletePurchaseById(purchase.getId());
        }
    }

    @Test
    public void testGetPurchaseListOnDate(){
        Purchase testPurchase1 = new Purchase();
        testPurchase1.setName("TestName1");
        testPurchase1.setDateFrom(Date.valueOf(LocalDate.now()));
        testPurchase1.setDateTo(Date.valueOf(LocalDate.now()));
        purchaseService.createPurchase(testPurchase1);

        Purchase testPurchase2 = new Purchase();
        testPurchase2.setName("TestName2");
        testPurchase2.setDateFrom(Date.valueOf(LocalDate.now()));
        testPurchase2.setDateTo(Date.valueOf(LocalDate.now()));
        testPurchase2.setDateWhen(Date.valueOf(LocalDate.now()));
        purchaseService.createPurchase(testPurchase2);

        List<Purchase> list = purchaseService.getPurchaseListOnDate(Date.valueOf(LocalDate.now()));

        assertThat(list.stream().anyMatch(o -> o.getName().equals("TestName1"))).isEqualTo(true);
        assertThat(list.stream().anyMatch(o -> o.getName().equals("TestName2"))).isEqualTo(false);

        purchaseService.deletePurchaseById(testPurchase1.getId());
        purchaseService.deletePurchaseById(testPurchase2.getId());
    }

    @Test
    public void testGetFullPurchaseListOnDate(){
        Purchase testPurchase1 = new Purchase();
        testPurchase1.setName("TestName1");
        testPurchase1.setDateFrom(Date.valueOf(LocalDate.now()));
        testPurchase1.setDateTo(Date.valueOf(LocalDate.now()));
        purchaseService.createPurchase(testPurchase1);

        Purchase testPurchase2 = new Purchase();
        testPurchase2.setName("TestName2");
        testPurchase2.setDateFrom(Date.valueOf(LocalDate.now()));
        testPurchase2.setDateTo(Date.valueOf(LocalDate.now()));
        testPurchase2.setDateWhen(Date.valueOf(LocalDate.now()));
        purchaseService.createPurchase(testPurchase2);

        List<Purchase> list = purchaseService.getFullPurchaseListOnDate(Date.valueOf(LocalDate.now()));

        assertThat(list.stream().anyMatch(o -> o.getName().equals("TestName1"))).isEqualTo(true);
        assertThat(list.stream().anyMatch(o -> o.getName().equals("TestName2"))).isEqualTo(true);

        purchaseService.deletePurchaseById(testPurchase1.getId());
        purchaseService.deletePurchaseById(testPurchase2.getId());
    }

    @Test
    public void testGetHistory(){
        Purchase testPurchase1 = new Purchase();
        testPurchase1.setName("TestName1");
        testPurchase1.setDateFrom(Date.valueOf(LocalDate.now()));
        testPurchase1.setDateTo(Date.valueOf(LocalDate.now()));
        purchaseService.createPurchase(testPurchase1);

        Purchase testPurchase2 = new Purchase();
        testPurchase2.setName("TestName2");
        testPurchase2.setDateFrom(Date.valueOf(LocalDate.now()));
        testPurchase2.setDateTo(Date.valueOf(LocalDate.now()));
        testPurchase2.setDateWhen(Date.valueOf(LocalDate.now()));
        purchaseService.createPurchase(testPurchase2);

        List<Purchase> list = purchaseService.getHistory();

        assertThat(list.stream().anyMatch(o -> o.getName().equals("TestName1"))).isEqualTo(false);
        assertThat(list.stream().anyMatch(o -> o.getName().equals("TestName2"))).isEqualTo(true);

        purchaseService.deletePurchaseById(testPurchase1.getId());
        purchaseService.deletePurchaseById(testPurchase2.getId());
    }

    @Test
    public void testGetExpiredPurchaseList(){
        Purchase testPurchase1 = new Purchase();
        testPurchase1.setName("TestName1");
        testPurchase1.setDateFrom(Date.valueOf(LocalDate.now()));
        testPurchase1.setDateTo(Date.valueOf(LocalDate.now()));
        purchaseService.createPurchase(testPurchase1);

        Purchase testPurchase2 = new Purchase();
        testPurchase2.setName("TestName2");
        testPurchase2.setDateFrom(Date.valueOf(LocalDate.now().minusDays(1)));
        testPurchase2.setDateTo(Date.valueOf(LocalDate.now().minusDays(1)));
        purchaseService.createPurchase(testPurchase2);

        List<Purchase> list = purchaseService.getExpiredPurchaseList(Date.valueOf(LocalDate.now()));

        assertThat(list.stream().anyMatch(o -> o.getName().equals("TestName1"))).isEqualTo(false);
        assertThat(list.stream().anyMatch(o -> o.getName().equals("TestName2"))).isEqualTo(true);

        purchaseService.deletePurchaseById(testPurchase1.getId());
        purchaseService.deletePurchaseById(testPurchase2.getId());
    }

    @Test
    public void testGetListOfPastPurchase(){
        Purchase testPurchase1 = new Purchase();
        testPurchase1.setName("TestName1");
        testPurchase1.setDateFrom(Date.valueOf(LocalDate.now().minusDays(1)));
        testPurchase1.setDateTo(Date.valueOf(LocalDate.now().minusDays(1)));
        purchaseService.createPurchase(testPurchase1);

        Purchase testPurchase2 = new Purchase();
        testPurchase2.setName("TestName2");
        testPurchase2.setDateFrom(Date.valueOf(LocalDate.now().minusDays(1)));
        testPurchase2.setDateTo(Date.valueOf(LocalDate.now().minusDays(1)));
        testPurchase2.setDateWhen(Date.valueOf(LocalDate.now().minusDays(1)));
        purchaseService.createPurchase(testPurchase2);

        List<Purchase> list = purchaseService.getListOfPastPurchase(Date.valueOf(LocalDate.now().minusDays(1)));

        assertThat(list.stream().anyMatch(o -> o.getName().equals("TestName1"))).isEqualTo(true);
        assertThat(list.stream().anyMatch(o -> o.getName().equals("TestName2"))).isEqualTo(true);

        purchaseService.deletePurchaseById(testPurchase1.getId());
        purchaseService.deletePurchaseById(testPurchase2.getId());
    }
}
