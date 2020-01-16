package hospital.management.Hospital.service;

import hospital.management.Hospital.converter.DoctorInformationConverter;
import hospital.management.Hospital.dto.DoctorInformationDto;
import hospital.management.Hospital.exceptions.NotFoundException;
import hospital.management.Hospital.model.Appointment;
import hospital.management.Hospital.model.DoctorInformation;
import hospital.management.Hospital.model.User;
import hospital.management.Hospital.repository.AppointmentRepository;
import hospital.management.Hospital.repository.DoctorInformationRepository;
import hospital.management.Hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorInformationService {

    @Autowired
    private DoctorInformationRepository doctorInformationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Transactional
    public List<DoctorInformationDto> getAllDoctors() {
        return doctorInformationRepository.findAll().stream().map(DoctorInformationConverter::convertDoctorInfoToDTO).collect(Collectors.toList());
    }

    @Transactional
    public DoctorInformationDto getDoctor(Integer id) {
        DoctorInformation doctorInformation = doctorInformationRepository.findById(id).orElseThrow(NotFoundException::new);
        return DoctorInformationConverter.convertDoctorInfoToDTO(doctorInformation);
    }

    @Transactional
    public DoctorInformationDto createDoctor(DoctorInformationDto doctorInformationDto) {
        DoctorInformation doctorInformation = new DoctorInformation();
        doctorInformation.setWorkingSchedule(doctorInformationDto.getWorking_schedule());

        User user = userRepository.findById(doctorInformationDto.getDoctor()).orElseThrow(NotFoundException::new);

        doctorInformation.setUser(user);
        return DoctorInformationConverter.convertDoctorInfoToDTO(doctorInformationRepository.save(doctorInformation));
    }

    @Transactional
    public DoctorInformationDto updateDoctor(Integer id, DoctorInformationDto doctorInformationDto) {
        DoctorInformation doctorInformation = doctorInformationRepository.findById(id).orElseThrow(NotFoundException::new);
        doctorInformation.setWorkingSchedule(doctorInformationDto.getWorking_schedule());
        User user = userRepository.findById(doctorInformationDto.getDoctor()).orElseThrow(NotFoundException::new);
        doctorInformation.setUser(user);
        return DoctorInformationConverter.convertDoctorInfoToDTO(doctorInformationRepository.save(doctorInformation));
    }

    @Transactional
    public boolean deleteDoctor(Integer id) {
        List<Appointment> appointments = new ArrayList<>(appointmentRepository.findAll());
        for (Appointment appointment : appointments)
            if (appointment.getDoctor().getDoctor_information().getId().equals(id)) {
                appointmentRepository.deleteById(appointment.getId());
            }
        doctorInformationRepository.deleteById(id);
        return true;
    }
}
