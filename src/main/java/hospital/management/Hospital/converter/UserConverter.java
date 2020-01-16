package hospital.management.Hospital.converter;

import hospital.management.Hospital.dto.UserDto;
import hospital.management.Hospital.model.Role;
import hospital.management.Hospital.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public static User convertDTOtoUser(UserDto userDto)
    {
        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .full_name(userDto.getFull_name())
                .email(userDto.getEmail())
                .phone_number(userDto.getPhone_number())
                .build();
    }

    public static UserDto convertUserToDTO(User user)
    {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .full_name(user.getFull_name())
                .email(user.getEmail())
                .phone_number(user.getPhone_number())
                .role(user.getRole().getId())
                .build();
    }
}
