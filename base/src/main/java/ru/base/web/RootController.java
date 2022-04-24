package ru.base.web;

import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import ru.base.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class RootController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/login")
    public String login(ModelMap model,
                        @RequestParam(value = "error", required = false) boolean error,
                        @RequestParam(value = "message", required = false) String message) {
        model.put("error", error);
        model.put("message", message);

        return "login";
    }

    //@Secured("ROLE_ADMIN")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/")
    public RedirectView getUsers(HttpServletRequest request) throws MalformedURLException {
        return new RedirectView("index.html");
    }

    @GetMapping("/give")
    public RedirectView giveUsers(HttpServletRequest request) throws MalformedURLException {
        return new RedirectView("/base/index.html");
    }

}