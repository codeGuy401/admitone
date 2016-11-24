package com.patientping.domain;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Jacob Eid on 11/23/2016.
 */
public interface UserRepository extends CrudRepository<User,Integer> {

    public User findByUserName(String userName);
}
