package com.liftoff.gifter.data;

import com.liftoff.gifter.models.Occasion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OccasionRepository extends CrudRepository<Occasion, Integer>{
}
