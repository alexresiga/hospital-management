package hospital.management.Hospital.controller;

import hospital.management.Hospital.converter.UserConverter;
import hospital.management.Hospital.dto.UserDto;
import hospital.management.Hospital.model.Role;
import hospital.management.Hospital.model.User;
import hospital.management.Hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;

    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    UserDto getUser(@PathVariable String username,@RequestParam("password") String password) {

        User user = userService.getUser(username);
        if(user.getPassword().equals(password))
            return userConverter.convertUsertoDTO(user);
        return null;
    }

}
