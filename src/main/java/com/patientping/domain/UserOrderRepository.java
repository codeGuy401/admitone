package com.patientping.domain;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Jacob Eid on 11/23/2016.
 */
public interface UserOrderRepository extends CrudRepository<UserOrder,Integer> {

    public UserOrder findByEventIdAndUserId(Integer eventId,Integer userId);
}
