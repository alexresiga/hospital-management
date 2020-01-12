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
public class RoleDto implements Serializable {
    private Integer id;
    private String name;
}
