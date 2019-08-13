package com.example.demo.service;

import com.example.demo.entity.Purchase;
import com.example.demo.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public void createPurchase(Purchase purchase){
        purchaseRepository.save(purchase);
    }

    public void updatePurchase(Purchase purchase){
        purchaseRepository.save(purchase);
    }

    public void deletePurchaseById(Long purchaseId){
        purchaseRepository.deleteById(purchaseId);
    }

    public Purchase findById(Long purchaseId){
        return purchaseRepository.findById(purchaseId).orElse(null);
    }

    public List<Purchase> findAllByName(String name){
        return purchaseRepository.findAllByName(name);
    }

    public List<Purchase> getPurchaseListOnDate(Date date){
        return purchaseRepository.getPurchaseListOnDate(date);
    }

    public List<Purchase> getFullPurchaseListOnDate(Date date){
        return purchaseRepository.getFullPurchaseListOnDate(date);
    }

    public List<Purchase> getHistory(){
        return purchaseRepository.getHistory();
    }

    public List<Purchase> getListOfPastPurchase(Date date){
        return purchaseRepository.getListOfPastPurchase(date);
    }

    public List<Purchase> getExpiredPurchaseList(Date date){
        return purchaseRepository.getExpiredPurchaseList(date);
    }

    public List<Purchase> findByName(String name){
        return purchaseRepository.findByName(name);
    }
}
