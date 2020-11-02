package com.iet.przychodnia.UserAuthenticationSystem.repository;

import com.iet.przychodnia.UserAuthenticationSystem.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepository {

    int insertUser(UUID id, User user);

    default int insertUser(User user){
        UUID id = UUID.randomUUID();
        return insertUser(id, user);
    }

    List<User> selectAllUsers();

    Optional<User> selectUserById(UUID id);

    int deleteUserById(UUID id);

    int updateUserById(UUID id, User visit);

}
