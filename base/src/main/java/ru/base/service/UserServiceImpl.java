package ru.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import ru.base.model.User;
import ru.base.repository.UserRepository;
import ru.base.to.UserTo;
import ru.base.util.UserUtil;
import ru.base.util.exception.NotFoundException;

import java.util.Collection;


import static ru.base.util.ValidationUtil.checkNotFound;
import static ru.base.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    //private final PasswordEncoder passwordEncoder;

   /*  @Autowired
    private CacheManager cacheManager; */


    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
        //this.passwordEncoder = passwordEncoder;
    }

    @Override
    @CacheEvict(value = "users",allEntries = true)
    public User create(User user) {
        return repository.save(user);
        //return prepareAndSave(user);
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @Override
    @Cacheable("users")
    public Collection<User> getAll() {
        return repository.getAll();
    }

    @Override
    @CacheEvict(value = "users",allEntries = true)
    public void update(User user) throws NotFoundException {
        Assert.notNull(user, "user must not be null");
        //checkNotFoundWithId(repository.save(user), user.getId());
        prepareAndSave(user);
    }

    @Override
    @CacheEvict(value = "users",allEntries = true)
    //@Transactional
    public void update(UserTo userTo) throws NotFoundException {
        User user = get(userTo.getId());
        //repository.save(UserUtil.updateFromTo(user,userTo));
        prepareAndSave(UserUtil.updateFromTo(user, userTo));
    }


    @Override
    public void evictCache() {
        //cacheManager.getCache("users").clear();
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        update(user);
    }

    private User prepareAndSave(User user) {
        //return repository.save(prepareToSave(user, passwordEncoder));
        return null;
    }

    
}



