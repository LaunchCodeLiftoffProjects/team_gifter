package com.liftoff.gifter.data;

import com.liftoff.gifter.models.Recipient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RecipientRepository extends CrudRepository<Recipient, Integer> {
}
