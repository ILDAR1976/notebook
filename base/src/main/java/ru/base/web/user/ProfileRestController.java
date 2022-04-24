package ru.base.web.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.base.model.User;

@RestController
@RequestMapping(ProfileRestController.REST_URL)

public class ProfileRestController extends AbstractUserController {

    public static final String REST_URL = "/rest/profile";

   

}