package com.example.demo.controller;

import com.example.demo.entity.Purchase;
import com.example.demo.service.PurchaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
public class PurchasesController {
    @Autowired
    private PurchaseService purchaseService;

    @PutMapping("purchase")
    public String updatePurchase(@RequestBody Purchase purchase){
        purchaseService.updatePurchase(purchase);
        return "redirect:/";
    }

    @PostMapping("purchase")
    public String createPurchase(@RequestBody Purchase purchase){
        purchaseService.createPurchase(purchase);
        return "redirect:/";
    }

    @RequestMapping(value = {"/", "index"})
    public String index(Model model){
        Date date = Date.valueOf(LocalDate.now());
        List<Purchase> purchases = purchaseService.getPurchaseListOnDate(date);
        model.addAttribute("purchases", purchases);
        model.addAttribute("type", "cur");
        return "index";
    }

    @RequestMapping(value = {"purchases/today"})
    public String getFullListPurchasesOnToday(Model model){
        Date date = Date.valueOf(LocalDate.now());
        List<Purchase> purchases = purchaseService.getFullPurchaseListOnDate(date);
        model.addAttribute("purchases", purchases);
        model.addAttribute("type", "fcur");
        return "index";
    }

    @RequestMapping(value = {"purchases/tomorrow"})
    public String getListPurchasesOnTomorrow(Model model){
        Date date = Date.valueOf(LocalDate.now().plusDays(1));
        List<Purchase> purchases = purchaseService.getPurchaseListOnDate(date);
        model.addAttribute("purchases", purchases);
        return "operations/historyList";
    }

    @RequestMapping(value = {"purchases/yesterday"})
    public String getListPurchasesOnYesterday(Model model){
        Date date = Date.valueOf(LocalDate.now().minusDays(1));
        List<Purchase> purchases = purchaseService.getListOfPastPurchase(date);
        model.addAttribute("purchases", purchases);
        return "operations/historyList";
    }

    @RequestMapping(value = {"purchases/expired"})
    public String getExpiredPurchaseList(Model model){
        Date date = Date.valueOf(LocalDate.now());
        List<Purchase> purchases = purchaseService.getExpiredPurchaseList(date);
        model.addAttribute("purchases", purchases);
        model.addAttribute("type", "exp");
        return "operations/expiredPurchaseList";
    }

    @RequestMapping(value = {"purchases/history"})
    public String getHistory(Model model){
        List<Purchase> purchases = purchaseService.getHistory();
        model.addAttribute("purchases", purchases);
        return "operations/historyList";
    }

    @RequestMapping(value = {"/purchase/edit/{id}"}, method = RequestMethod.GET)
    public String showEditPurchaseForm(@PathVariable("id") Long purchaseId, Model model){
        Purchase purchase = purchaseService.findById(purchaseId);
        model.addAttribute("purchase", purchase);
        return "operations/editPurchase";
    }

    @RequestMapping(value = {"/purchase/delete/{id}"}, method = RequestMethod.DELETE)
    public String deletePurchase(@PathVariable("id") Long purchaseId, Model model){
        purchaseService.deletePurchaseById(purchaseId);
        return "index";
    }

    @RequestMapping(value = {"/purchase/buy/{id}"}, method = RequestMethod.PUT)
    public String buyPurchase(@PathVariable("id") Long purchaseId, Model model){
        Date date = Date.valueOf(LocalDate.now());
        Purchase purchase = purchaseService.findById(purchaseId);
        purchase.setDateWhen(date);
        purchaseService.updatePurchase(purchase);
        return "redirect:/";
    }

    @RequestMapping(value = {"/purchase/reject/{id}"}, method = RequestMethod.PUT)
    public String rejectPurchase(@PathVariable("id") Long purchaseId, Model model){
        Purchase purchase = purchaseService.findById(purchaseId);
        purchase.setDateWhen(null);
        purchaseService.updatePurchase(purchase);
        return "redirect:/";
    }

    @RequestMapping(value = {"/purchase/new"})
    public String showAddPurchaseForm(Model model){
        Purchase purchase = new Purchase();
        model.addAttribute("purchase", purchase);
        return "operations/newPurchase";
    }


}
