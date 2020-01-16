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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoomRepository roomRepository;

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
    public AppointmentDto updateAppointmentStatus(Integer id, String status) {
      Appointment appointment = appointmentRepository.findById(id).orElse(null);
      if (appointment != null) {
        appointment.setApproved(status);
        appointmentRepository.save(appointment);
      }
      return getAppointmentById(id);
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

    @Transactional
    public AppointmentDto createAppointment(AppointmentDto appointmentDto) {
      User doctor = userRepository.findById(appointmentDto.getDoctor()).orElse(null);
      User patient = userRepository.findById(appointmentDto.getPatient()).orElse(null);
      Room room = roomRepository.findById(appointmentDto.getRoom()).orElse(null);
      if (doctor == null || patient == null || room == null) {
        return null;
      } else {
        Appointment appointment = new Appointment(null, doctor, patient, appointmentDto.getDate(), room, "pending");
        appointmentRepository.save(appointment);
        return AppointmentConverter.convertAppointmentToDto(appointment);
      }
    }
}
