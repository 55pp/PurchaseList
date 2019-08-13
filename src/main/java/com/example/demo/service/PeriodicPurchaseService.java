package com.example.demo.service;

import com.example.demo.entity.PeriodicPurchase;
import com.example.demo.repository.PeriodicPurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class PeriodicPurchaseService {
    @Autowired
    private final PeriodicPurchaseRepository periodicPurchaseRepository;

    public PeriodicPurchaseService(PeriodicPurchaseRepository periodicPurchaseRepository) {
        this.periodicPurchaseRepository = periodicPurchaseRepository;
    }

    public void createPeriod(PeriodicPurchase purchase){
        periodicPurchaseRepository.save(purchase);
    }

    public void updatePeriod(PeriodicPurchase purchase){
        periodicPurchaseRepository.save(purchase);
    }

    public void deletePeriodById(Long purchaseId){
        periodicPurchaseRepository.deleteById(purchaseId);
    }

    public List<PeriodicPurchase> findAll(){
        return periodicPurchaseRepository.findFull();
    }

    public PeriodicPurchase findById(Long id){
        return periodicPurchaseRepository.findById(id).orElse(null);
    }

    public List<PeriodicPurchase> findUnplannedPurchase(Date date){
        return periodicPurchaseRepository.findUnplannedPurchase(date);
    }
}
