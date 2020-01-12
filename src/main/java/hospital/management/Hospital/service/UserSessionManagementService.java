package hospital.management.Hospital.service;

import hospital.management.Hospital.converter.UserConverter;
import hospital.management.Hospital.dto.UserDto;
import hospital.management.Hospital.exceptions.NotFoundException;
import hospital.management.Hospital.model.User;
import hospital.management.Hospital.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserSessionManagementService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName())));
    }

    @Transactional
    public UserDto loadCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return UserConverter.convertUserToDTO(userRepository.findUserByEmail(email).orElseThrow(NotFoundException::new));
    }
}
