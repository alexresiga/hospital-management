package hospital.management.Hospital.service;

import hospital.management.Hospital.model.Role;
import hospital.management.Hospital.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Optional<Role> getRoleByName(String name) {
        return roleRepository.findRoleByName("user");
    }
}
