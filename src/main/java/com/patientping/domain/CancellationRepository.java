package com.patientping.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Jacob Eid on 11/23/2016.
 */
public interface CancellationRepository extends CrudRepository<Cancellation,Integer> {
    public List<Cancellation> findByUserId(Integer userId);
}
