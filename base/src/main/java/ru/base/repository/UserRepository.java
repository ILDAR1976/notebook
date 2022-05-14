package ru.base.repository;

import java.util.Collection;
import java.util.Optional;

import ru.base.model.User;

public interface UserRepository {
    User save(User user);
    boolean delete(int id);
    User get(int id);
    Optional<User> getByEmail(String email);
    Collection<User> getAll();
}