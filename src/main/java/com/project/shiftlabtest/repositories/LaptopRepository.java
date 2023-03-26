package com.project.shiftlabtest.repositories;

import com.project.shiftlabtest.models.Laptop;
import org.springframework.data.repository.CrudRepository;

public interface LaptopRepository extends CrudRepository<Laptop, Integer> {
}
