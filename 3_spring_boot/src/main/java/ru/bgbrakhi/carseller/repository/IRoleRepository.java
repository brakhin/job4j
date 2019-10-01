package ru.bgbrakhi.carseller.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bgbrakhi.carseller.models.City;
import ru.bgbrakhi.carseller.models.Role;

public interface IRoleRepository extends CrudRepository<Role, Long> {
}


