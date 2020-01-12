package hospital.management.Hospital.service;

import hospital.management.Hospital.repository.DoctorInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorInformationService {

    @Autowired
    private DoctorInformationRepository doctorInformationRepository;
}
