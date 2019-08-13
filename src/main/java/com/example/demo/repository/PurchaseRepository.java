package com.example.demo.repository;

import com.example.demo.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

//@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findAllByName(String name);

    @Query(value = "select * from purchase where datefrom <= :date and dateto >= :date and datewhen is null order by name", nativeQuery = true)
    List<Purchase> getPurchaseListOnDate(@Param("date") Date date);

    @Query(value = "select * from purchase where datefrom <= :date and dateto >= :date and (datewhen is null or datewhen = :date) order by datewhen desc, name", nativeQuery = true)
    List<Purchase> getFullPurchaseListOnDate(@Param("date") Date date);

    @Query(value = "select * from purchase where datewhen is not null order by datewhen desc, name", nativeQuery = true)
    List<Purchase> getHistory();

    @Query(value = "select * from purchase where datewhen = :date or dateto = :date order by datewhen desc, name", nativeQuery = true)
    List<Purchase> getListOfPastPurchase(@Param("date") Date date);

    @Query(value = "select * from purchase where dateto < :date and datewhen is null order by datewhen desc, name", nativeQuery = true)
    List<Purchase> getExpiredPurchaseList(@Param("date") Date date);

    @Query(value = "select * from purchase where name = :name", nativeQuery = true)
    List<Purchase> findByName(@Param("name") String name);

}
