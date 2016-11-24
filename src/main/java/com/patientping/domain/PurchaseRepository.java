package com.patientping.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Jacob Eid on 11/23/2016.
 */
public interface PurchaseRepository extends CrudRepository<Purchase,Integer> {

    public List<Purchase> findByUserId(Integer userId);
}
