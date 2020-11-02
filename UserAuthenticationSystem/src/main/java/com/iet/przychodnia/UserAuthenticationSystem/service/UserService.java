package com.iet.przychodnia.UserAuthenticationSystem.service;

import com.iet.przychodnia.UserAuthenticationSystem.model.User;
import com.iet.przychodnia.UserAuthenticationSystem.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserService(@Qualifier("list") IUserRepository userRepository){
        this.userRepository = userRepository;
    }

    public int addUser(User user){
        return userRepository.insertUser(user);
    }

    public List<User> getAllUsers(){
        return userRepository.selectAllUsers();
    }

    public Optional<User> getUserById(UUID id){
        return userRepository.selectUserById(id);
    }

    public int deleteUser (UUID id){
        return userRepository.deleteUserById(id);
    }

    public int updateUser (UUID id, User newUser){
        return userRepository.updateUserById(id, newUser);
    }
}
