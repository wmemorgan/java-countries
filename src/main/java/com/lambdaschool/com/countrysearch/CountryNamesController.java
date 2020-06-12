package com.lambdaschool.com.countrysearch;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/names")
public class CountryNamesController {
    // /all
    @GetMapping(value = "/all",
    produces = {"application/json"})
    public ResponseEntity<?> getAllCountries() {
        CountrysearchApplication
                .ourCountryList
                .countryList
                .sort((a1, a2) -> a1.getName().compareToIgnoreCase(a2.getName()));
        return new ResponseEntity<>(CountrysearchApplication.ourCountryList
                .countryList, HttpStatus.OK);
    }

    // /start/{letter}

    // /size/{number}
}
