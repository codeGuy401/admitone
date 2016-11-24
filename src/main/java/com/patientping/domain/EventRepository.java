package com.patientping.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Jacob Eid on 11/23/2016.
 */
public interface EventRepository extends CrudRepository<Event,Integer> {

    public List<Event> findByIdBetween(Integer startId,Integer endId);
}
