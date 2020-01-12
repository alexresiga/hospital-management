package hospital.management.Hospital.service;

import hospital.management.Hospital.converter.AppointmentConverter;
import hospital.management.Hospital.dto.AppointmentDto;
import hospital.management.Hospital.exceptions.ExceptionClass;
import hospital.management.Hospital.exceptions.NotFoundException;
import hospital.management.Hospital.model.Appointment;
import hospital.management.Hospital.model.Room;
import hospital.management.Hospital.model.User;
import hospital.management.Hospital.repository.AppointmentRepository;
import hospital.management.Hospital.repository.RoomRepository;
import hospital.management.Hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;


    @Transactional
    public List<AppointmentDto> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                        .map(AppointmentConverter::convertAppointmentToDto)
                        .collect(Collectors.toList());
    }

    @Transactional
    public AppointmentDto getAppointmentById(Integer id){
        return AppointmentConverter.convertAppointmentToDto(appointmentRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Transactional
    public List<AppointmentDto> getAppointmentsOfaPatient(Integer patient_id) {
        return appointmentRepository.findAll().stream()
                .filter(appointment -> appointment.getPatient().getId().equals(patient_id))
                .map(AppointmentConverter::convertAppointmentToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<AppointmentDto> getAppointmentsOfaDoctor(Integer doctor_id) {
        return appointmentRepository.findAll().stream()
                .filter(appointment -> appointment.getDoctor().getId().equals(doctor_id))
                .map(AppointmentConverter::convertAppointmentToDto)
                .collect(Collectors.toList());
    }

}
