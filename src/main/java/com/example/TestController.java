package com.example;

import com.gearsofleo.platform.aux.country.query.api.PlatformAuxCountryQueryApiProtos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gearsofleo.platform.aux.country.feign.client.CountryQueryClient;

@RestController
public class TestController{

    private final CountryQueryClient countryQueryClient;

    public TestController(CountryQueryClient countryQueryClient) {
        this.countryQueryClient = countryQueryClient;
    }

    @GetMapping("test")
    public ResponseEntity test(){
        PlatformAuxCountryQueryApiProtos.GetSelectedCountriesByAllBrandsDocument selectedCountriesByAllBrands = countryQueryClient.getSelectedCountriesByAllBrands();
        return ResponseEntity.status(HttpStatus.OK).body(selectedCountriesByAllBrands);
    }

}
