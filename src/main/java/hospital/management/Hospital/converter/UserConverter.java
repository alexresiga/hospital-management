package hospital.management.Hospital.converter;

import hospital.management.Hospital.dto.UserDto;
import hospital.management.Hospital.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User convertDTOtoUser(UserDto userDto)
    {
        return User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .build();
    }

    public UserDto convertUsertoDTO(User user)
    {
        return UserDto.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }
}
