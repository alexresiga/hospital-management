package hospital.management.Hospital.dto;


import hospital.management.Hospital.model.Role;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
@Builder
public class UserDto implements Serializable {
    private String username;
    private String password;
    private Role role;
}
