package hospital.management.Hospital.converter;

import hospital.management.Hospital.dto.RoomDto;
import hospital.management.Hospital.model.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomConverter {

    public static RoomDto convertRoomToDTO(Room room) {
        return RoomDto.builder()
                .id(room.getId())
                .name(room.getName())
                .level(room.getLevel())
                .department(room.getDepartment().getId())
                .build();
    }
}
