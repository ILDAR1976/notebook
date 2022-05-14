package ru.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.base.AuthorizedUser;
import ru.base.model.User;
import ru.base.repository.UserRepository;



@Service("userDetailServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    
   
    private final UserRepository userRepository;

    
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
        return new AuthorizedUser(user);
    }
}

