package com.users2.users2.Service;

import com.users2.users2.Entity.CountryEntity;

import java.util.Optional;

public interface CountryService {
    public CountryEntity save(CountryEntity countryEntity);
    public CountryEntity getById(int id);
}
