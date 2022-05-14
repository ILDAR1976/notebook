package ru.base.web.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.base.AuthorizedUser;
import ru.base.model.User;
import ru.base.repository.UserRepository;
import ru.base.to.AuthenticationRequestTo;
import ru.base.web.user.security.JwtTokenProvider;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = AuthenticationRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationRestController {
    public static final String REST_URL = "/api/auth";
    
    private final AuthenticationManager authenticationManager;
    private ru.base.repository.UserRepository userRepository;
    private JwtTokenProvider jwtTokenProvider;

    public AuthenticationRestController(AuthenticationManager authenticationManager, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestTo request){
        try {
            var auth = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            authenticationManager.authenticate(auth);
            User user = userRepository.getByEmail(request.getEmail()).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
            String role = user.getRoles().toArray()[0].toString();
            String token = jwtTokenProvider.createToken(request.getEmail(), role);
            Map<Object, Object> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
        }
    } 
    
    @PostMapping(value = "/logout", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void logout(HttpServletRequest request, HttpServletResponse response){
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}
