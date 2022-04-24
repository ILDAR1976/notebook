package ru.base.service;

import ru.base.model.User;
import ru.base.to.UserTo;
import ru.base.util.exception.NotFoundException;

import java.util.Collection;

public interface UserService {

    User create(User user) ;

    void delete(int id) throws NotFoundException ;

    User get(int id) throws NotFoundException ;

    User getByEmail(String email) throws NotFoundException;

    Collection<User> getAll();

    void update(User user) throws NotFoundException ;

    void evictCache();

    void enable(int id, boolean enabled);

    void update(UserTo userTo) throws NotFoundException ;;
}
