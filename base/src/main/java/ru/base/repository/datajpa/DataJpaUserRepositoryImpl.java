package ru.base.repository.datajpa;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import ru.base.model.User;
import ru.base.repository.UserRepository;

@Repository
public class DataJpaUserRepositoryImpl implements UserRepository {

    private static final Sort SORT_NAME_EMAIL = Sort.by("name","email");

    @Autowired
    ProxyUserRepository proxy;


    @Override
    public User save(User user) {
        return proxy.save(user);
    }

    @Override
    public boolean delete(int id) {
        try {
            proxy.deleteById(id);
        } catch(Exception exception) {
            return false;
        }
        return true;
    }

    @Override
    public User get(int id) {
        return proxy.findById(id).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return proxy.getByEmail(email);
    }

    @Override
    public Collection<User> getAll() {
        return proxy.findAll();
    }
}
