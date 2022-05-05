package ru.base.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.base.model.User;
import ru.base.service.UserServiceImpl;
import ru.base.to.UserTo;
import ru.base.util.UserUtil;

import java.util.Collection;

import static ru.base.util.ValidationUtil.assureIdConsistent;
import static ru.base.util.ValidationUtil.checkNew;


//@Controller
public abstract class AbstractUserController {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());
    public static final String EXCEPTION_DUPLICATE_EMAIL = "exception.user.duplicateEmail";

    @Autowired
    private UserServiceImpl service;

    public Collection<User> getAll() {
        LOG.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        LOG.info("get {}", id);
        return service.get(id);
    }

    public User create(User user) {
        LOG.info("create {}", user);
        checkNew(user);
        return service.create(user);
    }

    public User create(UserTo userTo) {
         return create(UserUtil.createNewFromTo(userTo));
    }
        


    public void delete(int id) {
        LOG.info("delete {}", id);
        service.delete(id);
    }

    public void update(UserTo userTo) {
        service.update(userTo);
    }

    public void update(User user, int id) {
        LOG.info("update {} with id={}", user, id);
        //assureIdConsistent(user, id);
        service.update(user);
    }

    public void update(UserTo userTo, int id) {
        service.update(userTo);
    }



    public User getByMail(String email) {
        LOG.info("getByEmail {}", email);
        return service.getByEmail(email);
    }

    public void enable(int id, boolean enabled){
        LOG.info((enabled ? "enable" : "disble") + id );
        service.enable(id,enabled);
    }
}