package com.users2.users2.Service;

import com.users2.users2.Entity.CountryEntity;
import com.users2.users2.Repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public CountryEntity save(CountryEntity countryEntity){
        return countryRepository.save(countryEntity);
    }
    public CountryEntity getById(int id){
        return countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found with id: " + id));
    }

}
