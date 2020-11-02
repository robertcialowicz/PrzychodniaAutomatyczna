package com.iet.przychodnia.UserAuthenticationSystem.repository;

import com.iet.przychodnia.UserAuthenticationSystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("MSSql")
public class UserRepositoryFromDB implements IUserRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryFromDB(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertUser(UUID id, User user) {
        return 0;
    }

    @Override
    public int insertUser(User user) {
        return 0;
    }

    @Override
    public List<User> selectAllUsers() {
        return null;
    }

    @Override
    public Optional<User> selectUserById(UUID id) {
        return Optional.empty();
    }

    @Override
    public int deleteUserById(UUID id) {
        return 0;
    }

    @Override
    public int updateUserById(UUID id, User visit) {
        return 0;
    }
}
