package hospital.management.Hospital.service;

import hospital.management.Hospital.converter.AppointmentConverter;
import hospital.management.Hospital.dto.AppointmentDto;
import hospital.management.Hospital.exceptions.ExceptionClass;
import hospital.management.Hospital.exceptions.NotFoundException;
import hospital.management.Hospital.model.*;
import hospital.management.Hospital.repository.*;
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
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientInformationRepository patientInformationRepository;

    @Autowired
    private DoctorInformationRepository doctorInformationRepository;


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

    @Transactional
    public AppointmentDto createAppointment(AppointmentDto appointmentDto){
        Appointment appointment = new Appointment();
        appointment.setApproved(appointmentDto.getApproved());
        appointment.setDate(appointmentDto.getDate());

        Room room = roomRepository.findById(appointmentDto.getRoom()).orElseThrow(NotFoundException::new);
        appointment.setRoom(room);

        User patient = userRepository.findById(appointmentDto.getPatient()).orElseThrow(NotFoundException::new);
        PatientInformation patientInformation = patientInformationRepository.findById(patient.getPatient_information().getId()).orElseThrow(NotFoundException::new);
        appointment.setPatient(patient);

        User doctor = userRepository.findById(appointmentDto.getDoctor()).orElseThrow(NotFoundException::new);
        DoctorInformation doctorInformation = doctorInformationRepository.findById(doctor.getDoctor_information().getId()).orElseThrow(NotFoundException::new);
        appointment.setDoctor(doctor);

        return AppointmentConverter.convertAppointmentToDto(appointmentRepository.save(appointment));
    }

}
