package com.lambdaschool.com.countrysearch;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/age")
public class CountryAgeController {
    // /size/{number}
    @GetMapping(value = "/age/{age}",
            produces = {"application/json"})
    public ResponseEntity<?> getByAge(@PathVariable int age) {
        List<Country> countries = CountrysearchApplication
                .ourCountryList
                .filterCountries(c ->
                        c.getMedianAge() >= age);

        countries.sort((c1, c2) -> (c1.getMedianAge() - c2.getMedianAge()));

        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    // /min
    @GetMapping(value = "/min",
            produces = {"application/json"})
    public ResponseEntity<?> getMinAge() {
        CountrysearchApplication
                .ourCountryList
                .countryList
                .sort((c1, c2) -> (c1.getMedianAge() - c2.getMedianAge()));

        Country minCountry = CountrysearchApplication.ourCountryList.countryList.get(0);

        return new ResponseEntity<>(minCountry, HttpStatus.OK);
    }
//
//    // /max
//    @GetMapping(value = "/max",
//            produces = {"application/json"})
//    public ResponseEntity<?> getMaxPopulatedCountry() {
//        CountrysearchApplication
//                .ourCountryList
//                .countryList
//                .sort((c1, c2) -> (int)(c2.getPopulation() - c1.getPopulation()));
//
//        Country maxCountry = CountrysearchApplication.ourCountryList.countryList.get(0);
//
//        return new ResponseEntity<>(maxCountry, HttpStatus.OK);
//    }
}
