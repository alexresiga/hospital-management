package hospital.management.Hospital.converter;

import hospital.management.Hospital.dto.DepartmentDto;
import hospital.management.Hospital.model.Department;
import hospital.management.Hospital.model.Room;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DepartmentConverter {

    public static DepartmentDto convertDepartmentToDTO(Department department) {

        return DepartmentDto.builder()
                .id(department.getId())
                .name(department.getName())
                .rooms(department.getRooms().stream().map(Room::getId).collect(Collectors.toList()))
                .build();
    }
}
