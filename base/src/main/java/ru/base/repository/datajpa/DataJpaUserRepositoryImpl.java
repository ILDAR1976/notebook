package ru.base.repository.datajpa;

import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import ru.base.model.User;
import ru.base.repository.UserRepository;

@Repository
public class DataJpaUserRepositoryImpl implements UserRepository {

    private static final Sort SORT_NAME_EMAIL = Sort.by("name","email");
    protected final Logger LOG = LoggerFactory.getLogger(getClass());
    
    @Autowired
    ProxyUserRepository proxy;


    @Override
    public User save(User user) {
        LOG.info("PASSWORD {} and USER {}", user.getPassword(), user.getEmail() );
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
        User user = proxy.findById(id).orElse(null);
        LOG.info("PASSWORD {} and USER {}", user.getPassword(), user.getEmail());
        return user;
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return proxy.getByEmail(email);
    }

    @Override
    public Collection<User> getAll() {
        return proxy.findAll();
    }
}
