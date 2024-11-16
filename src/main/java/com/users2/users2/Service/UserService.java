package com.users2.users2.Service;

import java.util.List;
import java.util.Optional;

import com.users2.users2.Entity.UserEntity;

public interface UserService {
    public UserEntity save(UserEntity userEntity);
    public String delete(int id);
    public UserEntity getById(int id);
    public List<UserEntity> getAll();
    public String login(UserEntity userEntity);
}
