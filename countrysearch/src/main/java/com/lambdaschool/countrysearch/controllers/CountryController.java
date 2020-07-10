package com.lambdaschool.countrysearch.controllers;

import com.lambdaschool.countrysearch.models.Country;
import com.lambdaschool.countrysearch.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryController {

    @Autowired
    CountryRepository countryRepository;

    private List<Country> filterCountries (List<Country> list, CheckCountry tester) {
        List<Country> tempList = new ArrayList<>();

        for (Country c: list) {
            if(tester.test(c)) {
                tempList.add(c);
            }
        }

        return tempList;
    }

    // http://localhost:2019/names/all
    @GetMapping(value="/names/all", produces = "application/json")
    public ResponseEntity<?> listCountriesByName() {
        List<Country> myList = new ArrayList<>();

        countryRepository.findAll().iterator().forEachRemaining(myList::add);
        myList.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));

        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    // http://localhost:2019/names/start/u
    @GetMapping(value = "/names/start/{letter}", produces = "application/json")
    public ResponseEntity<?> listAllByFirstName(@PathVariable char letter) {
        List<Country> myList = new ArrayList<>();

        countryRepository.findAll().iterator().forEachRemaining(myList::add);
        List<Country> filteredList = filterCountries(myList,
                c -> c.getName().toLowerCase().charAt(0) == Character.toLowerCase(letter));

        filteredList.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));

        return new ResponseEntity<>(filteredList, HttpStatus.OK);
    }

    // http://localhost:2019/population/total
    @GetMapping(value = "/population/total", produces = "application/json")
    public ResponseEntity<?> getTotalPopulation() {
        List<Country> myList = new ArrayList<>();

        countryRepository.findAll().iterator().forEachRemaining(myList::add);

        Long sum = myList.stream()
                .map(c -> c.getPopulation())
                .reduce(0L, Long::sum);

        System.out.println("The Total Population is " + sum);

        return new ResponseEntity<>(sum, HttpStatus.OK);
    }

    // http://localhost:2019/population/min
    @GetMapping(value = "/population/min", produces = "application/json")
    public ResponseEntity<?> getMinPopulation() {
        List<Country> myList = new ArrayList<>();

        countryRepository.findAll().iterator().forEachRemaining(myList::add);

        myList.sort((c1, c2) -> (int)(c1.getPopulation() - c2.getPopulation()));
        Country minPopulation = myList.get(0);

        return new ResponseEntity<>(minPopulation, HttpStatus.OK);
    }

    // http://localhost:2019/population/max
    @GetMapping(value = "/population/max", produces = "application/json")
    public ResponseEntity<?> getMaxPopulation() {
        List<Country> myList = new ArrayList<>();

        countryRepository.findAll().iterator().forEachRemaining(myList::add);

        myList.sort((c1, c2) -> (int)(c1.getPopulation() - c2.getPopulation()));
        Country maxPopulation = myList.get(myList.size()-1);

        return new ResponseEntity<>(maxPopulation, HttpStatus.OK);
    }
}
