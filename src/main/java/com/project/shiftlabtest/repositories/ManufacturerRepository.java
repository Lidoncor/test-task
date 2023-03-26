package com.project.shiftlabtest.repositories;

import com.project.shiftlabtest.models.Manufacturer;
import org.springframework.data.repository.CrudRepository;

public interface ManufacturerRepository extends CrudRepository<Manufacturer, Integer> {
}
