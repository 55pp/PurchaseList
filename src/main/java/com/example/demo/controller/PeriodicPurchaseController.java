package com.example.demo.controller;

import com.example.demo.entity.PeriodicPurchase;
import com.example.demo.service.PeriodicPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PeriodicPurchaseController {
    @Autowired
    private PeriodicPurchaseService periodicPurchaseService;

    @RequestMapping(value = "periods")
    public String getPeriods(Model model){
        List<PeriodicPurchase> periods = periodicPurchaseService.findAll();
        model.addAttribute("periods", periods);
        return "operations/periodList";
    }

    @PutMapping("period")
    public String updatePurchase(@RequestBody PeriodicPurchase periodicPurchase){
        periodicPurchaseService.updatePeriod(periodicPurchase);
        return "redirect:/";
    }

    @PostMapping("period")
    public String createPurchase(@RequestBody PeriodicPurchase periodicPurchase){
        periodicPurchaseService.createPeriod(periodicPurchase);
        return "redirect:/";
    }

    @RequestMapping(value = {"/period/edit/{id}"}, method = RequestMethod.GET)
    public String showEditPurchaseForm(@PathVariable("id") Long purchaseId, Model model){
        PeriodicPurchase period = periodicPurchaseService.findById(purchaseId);
        model.addAttribute("period", period);
        return "operations/editPeriod";
    }

    @RequestMapping(value = {"/period/delete/{id}"}, method = RequestMethod.DELETE)
    public String deletePurchase(@PathVariable("id") Long periodId, Model model){
        periodicPurchaseService.deletePeriodById(periodId);

        return "redirect:/";
    }

    @RequestMapping(value = {"/period/new"})
    public String showAddPurchaseForm(Model model){
        PeriodicPurchase period = new PeriodicPurchase();
        model.addAttribute("period", period);
        return "operations/newPeriod";
    }
}
