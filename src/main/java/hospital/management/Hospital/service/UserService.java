package hospital.management.Hospital.service;

import hospital.management.Hospital.converter.UserConverter;
import hospital.management.Hospital.dto.UserDto;
import hospital.management.Hospital.model.User;
import hospital.management.Hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public User getUser(Integer id) {
        return userRepository.findById(id).isPresent() ? userRepository.findById(id).get() : null;
    }

    @Transactional
    public void changePassword(User user) {
        userRepository.findById(user.getId()).ifPresent(user_found -> user_found.setPassword(user.getPassword()));
    }

    @Transactional
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username).isPresent() ? userRepository.findUserByUsername(username).get() : null;
        return user != null ? UserConverter.convertUserToDTO(user) : null;
    }

}
