package ru.base.web.user;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ru.base.AuthorizedUser;
import ru.base.model.Role;
import ru.base.model.User;
import ru.base.web.SecurityUtil;

import static  ru.base.web.user.AdminRestController.REST_URL;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserRestController extends AbstractUserController {

    @RequestMapping(value = "/text",method = RequestMethod.GET)
    public String testUTF(){
        return "Русский текст";
    }

    @PostMapping(value = "/user/whoami")
    public String whoAmI() {
        AuthorizedUser authorizedUser = SecurityUtil.get();
        User user = super.get(authorizedUser.getUserTo().getId());
        if (user.getRoles().contains(Role.ROLE_ADMIN)) {
            return "1";
        } else {
            return "0";
        }
    }

    @PostMapping(value = "/user/register",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createWithLocation(@RequestBody User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);
        user.setRoles(roles);
        user.setId(null);
        User created =  super.create(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

}
