package com.lambdaschool.com.countrysearch;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/population")
public class CountryPopulationController {
    // /size/{number}
    @GetMapping(value = "/size/{people}",
            produces = {"application/json"})
    public ResponseEntity<?> getByPopulationSize(@PathVariable int people) {
        List<Country> countries = CountrysearchApplication
                .ourCountryList
                .filterCountries(c ->
                        c.getPopulation() >= people);

        countries.sort((c1, c2) -> (int)(c1.getPopulation() - c2.getPopulation()));

        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    // /min
    @GetMapping(value = "/min",
            produces = {"application/json"})
    public ResponseEntity<?> getMinPopulatedCountry() {
        CountrysearchApplication
                .ourCountryList
                .countryList
                .sort((c1, c2) -> (int)(c1.getPopulation() - c2.getPopulation()));

        Country minCountry = CountrysearchApplication.ourCountryList.countryList.get(0);

        return new ResponseEntity<>(minCountry, HttpStatus.OK);
    }

    // /max
    @GetMapping(value = "/max",
            produces = {"application/json"})
    public ResponseEntity<?> getMaxPopulatedCountry() {
        CountrysearchApplication
                .ourCountryList
                .countryList
                .sort((c1, c2) -> (int)(c2.getPopulation() - c1.getPopulation()));

        Country maxCountry = CountrysearchApplication.ourCountryList.countryList.get(0);

        return new ResponseEntity<>(maxCountry, HttpStatus.OK);
    }
}
