package com.liftoff.gifter.data;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.liftoff.gifter.models.Gift;

@Repository
@Transactional
public interface GiftRepository extends CrudRepository<Gift, Integer> {

}
