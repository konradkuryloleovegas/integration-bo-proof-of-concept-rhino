package com.example;

import com.gearsofleo.platform.aux.country.query.api.PlatformAuxCountryQueryApiProtos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.gearsofleo.platform.aux.country.feign.client.CountryQueryClient;

@RestController
public class TestController{

    private final CountryQueryClient countryQueryClient;
    private final SessionMemoryStorage sessionMemoryStorage;

    public TestController(CountryQueryClient countryQueryClient, SessionMemoryStorage sessionMemoryStorage) {
        this.countryQueryClient = countryQueryClient;
        this.sessionMemoryStorage = sessionMemoryStorage;
    }

    @GetMapping("test/{input}")
    public ResponseEntity test(@PathVariable("input") String input){
        sessionMemoryStorage.setSessionStorage(input);
        PlatformAuxCountryQueryApiProtos.GetSelectedCountriesByAllBrandsDocument selectedCountriesByAllBrands = countryQueryClient.getSelectedCountriesByAllBrands();
        return ResponseEntity.status(HttpStatus.OK).body(selectedCountriesByAllBrands);
    }

}
