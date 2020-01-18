package hospital.management.Hospital.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
@Builder
public class SignupUserDto implements Serializable {
    private String password;
    private String fullName;
    private String email;
}