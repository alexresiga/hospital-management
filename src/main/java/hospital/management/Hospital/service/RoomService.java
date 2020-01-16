package hospital.management.Hospital.service;

import hospital.management.Hospital.converter.RoomConverter;
import hospital.management.Hospital.dto.RoomDto;
import hospital.management.Hospital.exceptions.NotFoundException;
import hospital.management.Hospital.model.Room;
import hospital.management.Hospital.repository.DepartmentRepository;
import hospital.management.Hospital.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional
    public List<RoomDto> getAllRooms() {
        return roomRepository.findAll().stream().map(RoomConverter::convertRoomToDTO).collect(Collectors.toList());
    }

    @Transactional
    public RoomDto getRoomById(Integer id) {
        Room room = roomRepository.findById(id).orElseThrow(NotFoundException::new);
        return RoomConverter.convertRoomToDTO(room);
    }

    @Transactional
    public RoomDto createRoom(RoomDto roomDto) {
        Room room = new Room(null, departmentRepository.findById(roomDto.getDepartment()).orElse(null), roomDto.getName(), roomDto.getLevel());
        return RoomConverter.convertRoomToDTO(room);
    }

    @Transactional
    public RoomDto updateRoom(Integer id, RoomDto roomDto) {
        Room room = roomRepository.findById(id).orElseThrow(NotFoundException::new);
        room.setName(roomDto.getName());
        room.setLevel(roomDto.getLevel());
        room.setDepartment(departmentRepository.findById(roomDto.getDepartment()).orElse(null));

        roomRepository.save(room);
        return RoomConverter.convertRoomToDTO(room);
    }

    @Transactional
    public boolean deleteRoom(Integer id) {
        roomRepository.deleteById(id);
        return true;
    }
}
