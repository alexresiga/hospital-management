package hospital.management.Hospital.controller;

import hospital.management.Hospital.service.MedicalLetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicalLetterController {

    @Autowired
    private MedicalLetterService medicalLetterService;
}
