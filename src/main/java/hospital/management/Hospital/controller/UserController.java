package hospital.management.Hospital.controller;

import hospital.management.Hospital.converter.UserConverter;
import hospital.management.Hospital.dto.UserDto;
import hospital.management.Hospital.model.User;
import hospital.management.Hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/user/{username}", method = RequestMethod.GET)
    UserDto getUser(@PathVariable String username) {

        User user = userService.getUser(username);
//        if(user.getPassword().equals(user.getPassword()))
//            return UserConverter.convertUserToDTO(user);
        return UserConverter.convertUserToDTO(user);

    }

}
