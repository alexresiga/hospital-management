package hospital.management.Hospital.controller;

import hospital.management.Hospital.dto.UserDto;
import hospital.management.Hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/user/{username}")
    public UserDto getUser(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/api/users/{id}")
    public UserDto getUserById(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping("/api/users")
    public UserDto createUser(@RequestBody UserDto data) {
        return userService.createUser(data);
    }

    @DeleteMapping("/api/users/{id}")
    public boolean deleteUser(@PathVariable("id") Integer id) {
        return userService.deleteUser(id);
    }

}
