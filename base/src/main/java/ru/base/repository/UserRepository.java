package ru.base.repository;

import java.util.Collection;

import ru.base.model.User;

public interface UserRepository {
    User save(User user);
    boolean delete(int id);
    User get(int id);
    User getByEmail(String email);
    Collection<User> getAll();
}