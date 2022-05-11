package ru.base.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import ru.base.service.UserServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
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

    @GetMapping("")
    public RedirectView redirectAllToLoginPage() {
        return new RedirectView("/base/index.html");
    } 

    @GetMapping("/error")
    public RedirectView redirectAllErrorsToLoginPage() {
        return new RedirectView("/base/index.html");
    } 

    @PostMapping("/logout")
    @ResponseBody
    public RedirectView postLgout(HttpServletRequest request) {
        return new RedirectView("/base/index.html");
    }

}