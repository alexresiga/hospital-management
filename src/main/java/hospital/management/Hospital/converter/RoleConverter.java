package hospital.management.Hospital.converter;

import hospital.management.Hospital.dto.RoleDto;
import hospital.management.Hospital.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {

    public static RoleDto convertRoleToDTO(Role role){
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
}
