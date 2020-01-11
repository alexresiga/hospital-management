package hospital.management.Hospital.service;

import hospital.management.Hospital.repository.PatientInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientInformationService {

    @Autowired
    private PatientInformationRepository patientInformationRepository;
}
