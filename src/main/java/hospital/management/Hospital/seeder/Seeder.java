package hospital.management.Hospital.seeder;

import hospital.management.Hospital.model.Role;
import hospital.management.Hospital.model.User;
import hospital.management.Hospital.repository.RoleRepository;
import hospital.management.Hospital.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class Seeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {

        Role admin = new Role(1,"admin");
        Role user = new Role(2,"user");
        if (roleRepository.findAll().isEmpty()) {
            roleRepository.save(admin);
            roleRepository.save(user);
        }

        if (userRepository.findAll().isEmpty()) {
            /*userRepository.save(new User("admin", passwordEncoder.encode("pass"),
                    "ADMIN", "admin@yahoo.com", "0746", admin));
            userRepository.save(new User("user", passwordEncoder.encode("pass"),
                    "User fullname", "user@yahoo.com", "0745", user));*/
        }



    }
}
