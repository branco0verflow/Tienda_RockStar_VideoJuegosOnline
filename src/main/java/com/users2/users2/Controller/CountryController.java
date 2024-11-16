package com.users2.users2.Controller;

import com.users2.users2.Entity.CountryEntity;
import com.users2.users2.Entity.UserEntity;
import com.users2.users2.Service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/countries")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @PostMapping("/addCountry")
    public ResponseEntity<?> addCountry(@RequestBody CountryEntity country){
        return ResponseEntity.status(HttpStatus.CREATED).body(countryService.save(country));
    }
}
