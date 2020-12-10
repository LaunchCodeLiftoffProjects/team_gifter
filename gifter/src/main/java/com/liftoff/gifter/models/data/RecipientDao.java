package com.liftoff.gifter.models.data;


import com.liftoff.gifter.models.Recipient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RecipientDao extends CrudRepository<Recipient, Integer> {
}
