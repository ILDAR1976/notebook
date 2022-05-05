package ru.base.web;

import java.net.MalformedURLException;
import java.security.Principal;
import java.util.Base64;
import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import ru.base.AuthorizedUser;
import ru.base.model.User;
import ru.base.service.UserService;
import ru.base.service.UserServiceImpl;
import ru.base.to.UserTo;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("/auth")
public class RootController {
    @Autowired
    @Qualifier("userService")
    private UserServiceImpl userService;

    @GetMapping("/login")
    public RedirectView getLoginPage() {
        return new RedirectView("/base/index.html");
    } 

    @GetMapping("/")
    public RedirectView redirectToLoginPage() {
        return new RedirectView("/base/index.html");
    } 


    @PostMapping(value = "/loginnew", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AuthorizedUser postLogin(@RequestBody UserTo userTo ) {
        var email = userTo.getName();
        AuthorizedUser authorizedUser = userService.loadUserByUsername(email);
        AuthorizedUser authorizedUserFromContext = SecurityUtil.get();
        if (authorizedUserFromContext.equals(authorizedUser)) {
            return authorizedUserFromContext;
        } else {
            return null;
        }
    }


    @GetMapping("/success")
    public String getSuccessPage() {
        return "";
    }


    @PostMapping("/logout")
    @ResponseBody
    public RedirectView postLgout(HttpServletRequest request) {
        return new RedirectView("/base/index.html");
    }

}