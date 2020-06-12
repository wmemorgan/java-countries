package com.lambdaschool.com.countrysearch;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @GetMapping(value = "/start/{letter}",
    produces = {"application/json"})
    public ResponseEntity<?> getCountriesByLetter(@PathVariable char letter) {
        List<Country> countries = CountrysearchApplication
                .ourCountryList
                .filterCountries(c ->
                        c.getName().toLowerCase().charAt(0) == Character.toLowerCase(letter));

        countries.sort((a1, a2) -> a1.getName().compareToIgnoreCase(a2.getName()));

        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    // /size/{number}
    @GetMapping(value = "/size/{number}",
            produces = {"application/json"})
    public ResponseEntity<?> getCountriesByNameLength(@PathVariable int number) {
        List<Country> countries = CountrysearchApplication
                .ourCountryList
                .filterCountries(c ->
                        c.getName().length() >= number);

        countries.sort((a1, a2) -> a1.getName().compareToIgnoreCase(a2.getName()));

        return new ResponseEntity<>(countries, HttpStatus.OK);
    }
}
