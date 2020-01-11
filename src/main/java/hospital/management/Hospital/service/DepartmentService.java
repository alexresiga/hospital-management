package hospital.management.Hospital.service;

import hospital.management.Hospital.converter.DepartmentConverter;
import hospital.management.Hospital.dto.DepartmentDto;
import hospital.management.Hospital.exceptions.NotFoundException;
import hospital.management.Hospital.model.Department;
import hospital.management.Hospital.model.Room;
import hospital.management.Hospital.repository.DepartmentRepository;
import hospital.management.Hospital.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Transactional
    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAll().stream().map(DepartmentConverter::convertDepartmentToDTO).collect(Collectors.toList());
    }

    @Transactional
    public DepartmentDto getDepartmentById(Integer id) {
        Department department = departmentRepository.findById(id).orElseThrow(NotFoundException::new);
        return DepartmentConverter.convertDepartmentToDTO(department);
    }

    @Transactional
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Set<Room> rooms = departmentDto.getRooms().stream().map(room -> roomRepository.findById(room).orElse(null)).collect(Collectors.toSet());
        Department department = new Department(null, rooms, departmentDto.getName());
        return DepartmentConverter.convertDepartmentToDTO(department);
    }

    @Transactional
    public DepartmentDto updateDepartment(Integer id, DepartmentDto departmentDto) {
        Department department = departmentRepository.findById(id).orElseThrow(NotFoundException::new);
        Set<Room> rooms = departmentDto.getRooms().stream().map(room -> roomRepository.findById(room).orElse(null)).collect(Collectors.toSet());
        department.setName(department.getName());
        department.setRooms(rooms);

        departmentRepository.save(department);
        return DepartmentConverter.convertDepartmentToDTO(department);
    }

    @Transactional
    public boolean deleteDepartment(Integer id) {
        departmentRepository.deleteById(id);
        return true;
    }
}
