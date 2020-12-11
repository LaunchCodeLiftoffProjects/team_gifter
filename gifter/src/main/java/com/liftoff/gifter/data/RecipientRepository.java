package com.liftoff.gifter.data;

import com.liftoff.gifter.models.Recipient;
import com.liftoff.gifter.models.User;
import org.springframework.data.repository.CrudRepository;

public interface RecipientRepository extends CrudRepository<Recipient, Integer> {

    Recipient findByFirstName(String firstName);

    Recipient findByLastName(String lastName);

}
