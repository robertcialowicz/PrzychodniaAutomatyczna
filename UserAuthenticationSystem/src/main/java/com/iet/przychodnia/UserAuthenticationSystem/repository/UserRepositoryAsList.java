package com.iet.przychodnia.UserAuthenticationSystem.repository;

import com.iet.przychodnia.UserAuthenticationSystem.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("list")
public class UserRepositoryAsList implements IUserRepository{

    private static List<User> DB = new ArrayList<>();

    @Override
    public int insertUser(UUID id, User user) {
        DB.add(new User(id, user.getName(), user.getSurname(), user.getEmail(), user.getPesel()));
        return 1;
    }

    @Override
    public List<User> selectAllUsers() {
        return DB;
    }

    @Override
    public Optional<User> selectUserById(UUID id) {
        return DB.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteUserById(UUID id) {
        Optional<User> userMaybe = selectUserById(id);
        if(userMaybe.isPresent()){
            DB.remove(userMaybe.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int updateUserById(UUID id, User userToUpdate) {
        return selectUserById(id)
                .map(user -> {
                    int indexOfUserToUpdate = DB.indexOf(user);
                    if(indexOfUserToUpdate>=0){
                        DB.set(indexOfUserToUpdate, new User(id, userToUpdate.getName(), userToUpdate.getSurname(), userToUpdate.getEmail(), userToUpdate.getPesel()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
