package com.users2.users2.Controller;

import com.users2.users2.Entity.CountryEntity;
import com.users2.users2.Service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.users2.users2.Entity.UserEntity;
import com.users2.users2.Service.UserService;

import java.util.Map;

@Controller
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private CountryService countryService;

    @PostMapping
    public ResponseEntity<?> addUsers(@RequestBody Map<String, Object> requestData){
        UserEntity user = new UserEntity();
        user.setEmail((String) requestData.get("email"));
        user.setPassword((String) requestData.get("password"));

        Integer countryId = (Integer) requestData.get("countryId");
        if (countryId != null) {
            CountryEntity country = countryService.getById(countryId);
            user.setCountry(country);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping("/{userId}/country/{countryId}")
    public ResponseEntity<?> linkCountryToUser(@PathVariable int userId, @PathVariable int countryId) {
        UserEntity user = userService.getById(userId);
        CountryEntity country = countryService.getById(countryId);
        user.setCountry(country);
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(user));
    }

    @PutMapping
    public ResponseEntity<?> updateUsers(@RequestBody UserEntity user){
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(user));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.delete(id));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getById(id));
    }
    @GetMapping
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserEntity userEntity){
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(userEntity));
    }
}
