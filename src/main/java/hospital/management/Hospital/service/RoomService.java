package hospital.management.Hospital.service;

import hospital.management.Hospital.converter.RoomConverter;
import hospital.management.Hospital.dto.RoomDto;
import hospital.management.Hospital.exceptions.NotFoundException;
import hospital.management.Hospital.model.Appointment;
import hospital.management.Hospital.model.Department;
import hospital.management.Hospital.model.Room;
import hospital.management.Hospital.repository.AppointmentRepository;
import hospital.management.Hospital.repository.DepartmentRepository;
import hospital.management.Hospital.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

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
        Department department = departmentRepository.findById(roomDto.getDepartment()).orElse(null);

        Room room = new Room();
        room.setName(roomDto.getName());
        room.setLevel(roomDto.getLevel());
        room.setDepartment(department);

        return RoomConverter.convertRoomToDTO(roomRepository.save(room));
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
        List<Appointment> appointments = new ArrayList<>(appointmentRepository.findAll());
        for(Appointment appointment: appointments)
            if(appointment.getRoom().getId().equals(id))
                appointmentRepository.deleteById(appointment.getId());
        roomRepository.deleteById(id);
        return true;
    }
}
