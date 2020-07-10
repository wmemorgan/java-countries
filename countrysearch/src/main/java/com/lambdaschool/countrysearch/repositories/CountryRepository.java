package com.lambdaschool.countrysearch.repositories;

import com.lambdaschool.countrysearch.models.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Long> {
}
