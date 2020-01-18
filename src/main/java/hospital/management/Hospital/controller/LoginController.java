package hospital.management.Hospital.controller;

import hospital.management.Hospital.dto.SignupUserDto;
import hospital.management.Hospital.dto.UserDto;
import hospital.management.Hospital.model.ErrorMessage;
import hospital.management.Hospital.model.Role;
import hospital.management.Hospital.repository.RoleRepository;
import hospital.management.Hospital.service.RoleService;
import hospital.management.Hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @PostMapping("/api/register")
    public ErrorMessage register(@RequestBody SignupUserDto data) {
        return userService.registerUser(data);
    }
}
