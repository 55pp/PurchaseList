package com.example.demo.service;

import com.example.demo.entity.PeriodicPurchase;
import com.example.demo.entity.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class SchedulerService {
    private static final String CRON = "* */10 * * * *";

    @Autowired
    private final PeriodicPurchaseService periodicPurchaseService;
    private final PurchaseService purchaseService;

    public SchedulerService(PeriodicPurchaseService periodicPurchaseService, PurchaseService purchaseService) {
        this.periodicPurchaseService = periodicPurchaseService;
        this.purchaseService = purchaseService;
    }

    @Scheduled(cron = CRON)
    public void checkPeriodicPurchase() {
        plannedPurchase(Date.valueOf(LocalDate.now()));
        plannedPurchase(Date.valueOf(LocalDate.now().plusDays(1)));
    }

    public void plannedPurchase(Date date){
        List<PeriodicPurchase> periodicPurchases = periodicPurchaseService.findUnplannedPurchase(date);
        for (PeriodicPurchase period : periodicPurchases) {
            Purchase purchase = new Purchase();
            purchase.setDateFrom(date);
            purchase.setDateTo(date);
//            purchase.setDateTo(Date.valueOf(date.toLocalDate().plusDays(period.getNumdays())));
            purchase.setName(period.getName());
            purchaseService.createPurchase(purchase);

            period.setDateLast(Date.valueOf(date.toLocalDate().plusDays(period.getNumdays())));
            periodicPurchaseService.updatePeriod(period);
        }
    }
}
