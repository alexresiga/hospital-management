package hospital.management.Hospital.service;
import hospital.management.Hospital.model.User;
import hospital.management.Hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    @Transactional
    public User getUser(String username) {
        return userRepository.findById(username).isPresent() ? userRepository.findById(username).get() : null;
    }

    @Transactional
    public void changePassword(User user) {
        userRepository.findById(user.getUsername()).ifPresent(user_found -> user_found.setPassword(user.getPassword()));
    }

}
