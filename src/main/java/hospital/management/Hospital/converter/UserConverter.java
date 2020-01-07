package hospital.management.Hospital.converter;

import hospital.management.Hospital.dto.UserDto;
import hospital.management.Hospital.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public static User convertDTOtoUser(UserDto userDto)
    {
        return User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .build();
    }

    public static UserDto convertUserToDTO(User user)
    {
        return UserDto.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }
}
