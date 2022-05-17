package ru.base.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

   /*  @RequestMapping("/error")
    public RedirectView handleError() {
        return new RedirectView("/base/index.html");
    }  */
}