package hospital.management.Hospital.controller;

import hospital.management.Hospital.converter.UserConverter;
import hospital.management.Hospital.dto.UserDto;
import hospital.management.Hospital.model.ErrorMessage;
import hospital.management.Hospital.model.Role;
import hospital.management.Hospital.repository.RoleRepository;
import hospital.management.Hospital.service.RoleService;
import hospital.management.Hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @PostMapping("/api/register")
    public ErrorMessage register(@RequestBody UserDto data) {
        return userService.registerUser(data);
    }

    @GetMapping("/api/currentUser")
    public UserDto currentUser(@AuthenticationPrincipal Principal principal) {
        System.out.println(principal.getName());
        System.out.println(userService.getUserByEmail(principal.getName()));
        return (userService.getUserByEmail(principal.getName()));
    }
}
