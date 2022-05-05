package ru.base;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;


import ru.base.model.Role;
import ru.base.model.User;
import ru.base.to.UserTo;
import ru.base.util.UserUtil;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    
    private static final long serialVersionUID = 1L;
    protected Set<Role> roles = Collections.singleton(Role.ROLE_USER);
    private UserTo userTo;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
        this.userTo = UserUtil.asTo(user);
    }

    public int getId() {
        return userTo.getId();
    }

    public void update(UserTo newTo) {
        userTo = newTo;
    }

    public UserTo getUserTo() {
        return userTo;
    }


    public Collection<GrantedAuthority> getAuthorities(int a) {

        Collection<GrantedAuthority>
                outList =roles.stream().
                map( x -> new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return x.name();
                    }
                }).collect(Collectors.toList());

        return outList;
    }


    @Override
    public String toString() {
        return userTo.toString();
    }
}

