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
    private Integer id;
    private String username;
    private String password;
    private String full_name;
    private String email;
    private String phone_number;
    private Integer role;
}
