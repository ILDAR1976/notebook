package ru.base.web.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.base.AuthorizedUser;
import ru.base.model.Role;
import ru.base.model.User;
import ru.base.web.SecurityUtil;

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
        LOG.info("!!! >>>> User: {}",authorizedUser.getUsername());
        if (user.getRoles().contains(Role.ROLE_ADMIN)) {
            return "1";
        } else {
            return "0";
        }
    }

}
