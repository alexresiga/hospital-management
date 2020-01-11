package hospital.management.Hospital.service;

import hospital.management.Hospital.model.MedicalLetter;
import hospital.management.Hospital.repository.MedicalLettersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalLetterService {

    @Autowired
    private MedicalLettersRepository medicalLettersRepository;
}
