package ru.base.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;


@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class RootController {

    /* 
    @GetMapping("/login")
    public RedirectView getLoginPage() {
        return new RedirectView("/base/index.html");
    } 

    @GetMapping("/")
    public RedirectView redirectToLoginPage() {
        return new RedirectView("/base/index.html");
    } 

    @GetMapping("/error/**")
    public RedirectView redirectAllToLoginPage() {
        return new RedirectView("/base/index.html");
    } 

    @GetMapping("/error")
    public RedirectView redirectAllErrorsToLoginPage() {
        return new RedirectView("/base/index.html");
    }  */

   
}