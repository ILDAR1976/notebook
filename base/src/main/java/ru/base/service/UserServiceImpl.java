package ru.base.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.ScopedProxyMode;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.base.AuthorizedUser;
import ru.base.model.User;
import ru.base.repository.UserRepository;
import ru.base.to.UserTo;
import ru.base.util.UserUtil;
import ru.base.util.exception.NotFoundException;

import java.util.Collection;


import static ru.base.util.ValidationUtil.checkNotFound;
import static ru.base.util.ValidationUtil.checkNotFoundWithId;
import static ru.base.util.UserUtil.prepareToSave;

@Service("userServiceImpl")
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserServiceImpl implements UserService, UserDetailsService {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    private final UserRepository repository;
    
    private final PasswordEncoder passwordEncoder;

   /*  @Autowired
    private CacheManager cacheManager; */

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    //@CacheEvict(value = "users",allEntries = true)
    public User create(User user) {
        return prepareAndSave(user);
    }

    @Override
    //@CacheEvict(value = "users", allEntries = true)
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        return checkNotFound(repository.getByEmail(email).orElseThrow(() ->
            new UsernameNotFoundException("User doesn't exists")), "email=" + email);
    }

    @Override
    //@Cacheable("users")
    public Collection<User> getAll() {
        return repository.getAll();
    }

    @Override
    //@CacheEvict(value = "users",allEntries = true)
    public void update(User user) throws NotFoundException {
        //Assert.notNull(user, "user must not be null");
        //checkNotFoundWithId(repository.save(user), user.getId());
        
        LOG.info("This is id={}",user.getId());
        prepareAndSave(user);
    }

    @Override
    //@CacheEvict(value = "users",allEntries = true)
    @Transactional
    public void update(UserTo userTo) throws NotFoundException {
        User user = get(userTo.getId());
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
        return repository.save(prepareToSave(user, passwordEncoder));
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase()).orElseThrow(() ->
        new UsernameNotFoundException("User doesn't exists"));
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }

    
}



