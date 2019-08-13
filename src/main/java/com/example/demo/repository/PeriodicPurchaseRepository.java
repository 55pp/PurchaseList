package com.example.demo.repository;


import com.example.demo.entity.PeriodicPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface PeriodicPurchaseRepository extends JpaRepository<PeriodicPurchase, Long> {

    @Query(value = "select * from periodicpurchase order by name", nativeQuery = true)
    List<PeriodicPurchase> findFull();

    @Query(value = "select * from periodicpurchase where datelast = :date", nativeQuery = true)
    List<PeriodicPurchase> findUnplannedPurchase(@Param("date") Date date);
}
