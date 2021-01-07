package com.liftoff.gifter.data;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface GiftRepository<Gift> extends CrudRepository<Gift, Integer> {

}
