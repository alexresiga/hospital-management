package hospital.management.Hospital.service;

import hospital.management.Hospital.converter.UserConverter;
import hospital.management.Hospital.dto.UserDto;
import hospital.management.Hospital.exceptions.NotFoundException;
import hospital.management.Hospital.model.Appointment;
import hospital.management.Hospital.model.ErrorMessage;
import hospital.management.Hospital.model.Role;
import hospital.management.Hospital.model.User;
import hospital.management.Hospital.repository.AppointmentRepository;
import hospital.management.Hospital.repository.RoleRepository;
import hospital.management.Hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Transactional
    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public List<UserDto> getUsers(){
        return userRepository.findAll().stream().map(UserConverter::convertUserToDTO).collect(Collectors.toList());
    }

    @Transactional
    public boolean deleteUser(Integer id) {
        List<Appointment> appointments = new ArrayList<>(appointmentRepository.findAll());
        for (Appointment appointment : appointments)
            if (appointment.getDoctor().getId().equals(id) || appointment.getPatient().getId().equals(id)) {
                appointmentRepository.deleteById(appointment.getId());
            }
        userRepository.deleteById(id);
        return true;
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

    @Transactional
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email).isPresent() ? userRepository.findUserByEmail(email).get() : null;
        return user != null ? UserConverter.convertUserToDTO(user) : null;
    }

    @Transactional
    public ErrorMessage registerUser(UserDto userDto) {
        Optional<Role> role = roleRepository.findRoleByName("user");
        role.ifPresent(value -> userDto.setRole(value.getId()));

        if (isEmailRegistered(userDto.getEmail())) {
            return ErrorMessage.builder().error(true).errorMessage("E-mail already registered.").build();
        }
        createUser(userDto);
        return ErrorMessage.builder().error(false).build();
    }

    @Transactional
    public boolean isUsernameRegistered(String username) {
        return userRepository.findUserByUsername(username).isPresent();
    }

    @Transactional
    public boolean isEmailRegistered(String email) {
        return userRepository.findUserByEmail(email).isPresent();
    }

    @Transactional
    public UserDto createUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setFull_name(userDto.getFull_name());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setPhone_number(userDto.getPhone_number());
        user.setRole(roleRepository.findById(userDto.getRole()).orElseThrow(NotFoundException::new));
        return UserConverter.convertUserToDTO(userRepository.save(user));
    }

    @Transactional
    public UserDto getUserById(Integer id) {
        return UserConverter.convertUserToDTO(userRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Transactional
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(UserConverter::convertUserToDTO).collect(Collectors.toList());
    }

}
