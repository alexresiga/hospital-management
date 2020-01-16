package hospital.management.Hospital.service;

import hospital.management.Hospital.converter.PatientInformationConverter;
import hospital.management.Hospital.dto.PatientInformationDto;
import hospital.management.Hospital.exceptions.NotFoundException;
import hospital.management.Hospital.model.Appointment;
import hospital.management.Hospital.model.PatientInformation;
import hospital.management.Hospital.model.User;
import hospital.management.Hospital.repository.AppointmentRepository;
import hospital.management.Hospital.repository.PatientInformationRepository;
import hospital.management.Hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientInformationService {

    @Autowired
    private PatientInformationRepository patientInformationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Transactional
    public List<PatientInformationDto> getAllPatients() {
        return patientInformationRepository.findAll().stream().map(PatientInformationConverter::convertPatientInformationToDTO).collect(Collectors.toList());
    }

    @Transactional
    public PatientInformationDto getPatient(Integer id) {
        PatientInformation PatientInformation = patientInformationRepository.findById(id).orElseThrow(NotFoundException::new);
        return PatientInformationConverter.convertPatientInformationToDTO(PatientInformation);
    }

    @Transactional
    public PatientInformationDto createPatient(PatientInformationDto PatientInformationDto) {
        PatientInformation PatientInformation = new PatientInformation();
        User user = userRepository.findById(PatientInformationDto.getPatient()).orElseThrow(NotFoundException::new);
        PatientInformation.setFields(PatientInformationDto.getFields());
        PatientInformation.setUser(user);
        return PatientInformationConverter.convertPatientInformationToDTO(patientInformationRepository.save(PatientInformation));
    }

    @Transactional
    public PatientInformationDto updatePatient(Integer id, PatientInformationDto PatientInformationDto) {
        PatientInformation PatientInformation = patientInformationRepository.findById(id).orElseThrow(NotFoundException::new);
        PatientInformation.setFields(PatientInformationDto.getFields());
        User user = userRepository.findById(PatientInformationDto.getPatient()).orElseThrow(NotFoundException::new);
        PatientInformation.setUser(user);
        return PatientInformationConverter.convertPatientInformationToDTO(patientInformationRepository.save(PatientInformation));
    }

    @Transactional
    public boolean deletePatient(Integer id) {
        List<Appointment> appointments = new ArrayList<>(appointmentRepository.findAll());
        for (Appointment appointment : appointments)
            if (appointment.getPatient().getPatient_information().getId().equals(id)) {
                appointmentRepository.deleteById(appointment.getId());
            }
        patientInformationRepository.deleteById(id);
        return true;
    }
}
