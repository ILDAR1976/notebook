package ru.base.web.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @RequestMapping(value = "/text",method = RequestMethod.GET)
    public String testUTF(){
        return "Русский текст";
    }

}
